	AREA	Closure, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	
	LDR R0, =ASize
	LDR R9, [R0]
	LDR R0, [R0]
	LDR R1, =AElems
	LDR R10,=AElems
	LDR R8, =0
	
	
	LDR R12, =0
	
while
	LDR R4, =0
	LDR R2, [R1]
	CMP R0, #0			;while( aSize >= 0 )
	BLS endWhile		;{
	SUB R0, R0, #1		;	aSize--;
scanWhile				;
	CMP R12, R9			;	while (scanCount < aSize)
	BHS endScan			;	{
	LDR R3, [R10, R4]	;		load next value to R3 using an offset;
	ADD R5, R3, R2		;		R5 = valueToScan + nextValue;
	CMP R5, #0			;		if( R5 == 0 )
	BNE notPair			;		{
	STR R8, [R1]		;			valueToScan = 0;
	STR R8, [R10, R4]	;			nextValue = 0;
	ADD R1, R1, #4		;			Address=+ 4;
	MOV R12,#0			;			scanCount = 0; 
	B	while			;			break; //breaks to start of outer while loop
						;		}
notPair					;		else
	ADD R12, R12, #1	;		{
	ADD R4, R4 , #4		;			scanCount =+ 1;		
	B scanWhile			;			offset =+ 4;
						;		}
endScan					;	}
	MOV R0, #0			;	R0 = 0; 
	;B stop				;	break; //breaks to end
						;}
endWhile				;
	MOV R0, #1			;R0 = 1;
	
	MOVS R0, #0x08000000
	MOVS R1, #0x00000005
	MOVS  R2, #17
	
	LDR R0, =0
; Keep dividing R2 by 2, while multiplying R1 by 2.
whileMUL
	CMP R2, #0
	BEQ endwhileMUL
	MOVS R2, R2, LSR #1	; Divide R2 by 2.
	
	; After dividing R2 by 2, (remainder is 0 or 1)
	; R1 * R2 := R1 * (2 * R2 + remainder) == 2 * R1 * R2 + (remainder ? R1 : 0)

	BCC endif1			; If remainder of 1.
	ADD R0, R1
endif1

	MOV R1, R1, LSL #1		; Multiply R1 by 2.

; Now, result ==  R1 * R2 + R0
	; So once R2 reaches 0, result == R0
	
	B whileMUL
endwhileMUL
	
	


stop	B	stop


	AREA	TestData, DATA, READWRITE

ASize	DCD	10						; Number of elements in A
AElems	DCD	+4,6,-4,+3,-8,0,+6,+8,-3,-8 ; Elements of A

	END
