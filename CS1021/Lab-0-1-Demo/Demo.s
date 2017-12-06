	AREA	Demo, CODE, READONLY
	IMPORT	main
	EXPORT	start

start	
	LDR R4, =NUMBER	; address
	LDR R0, =0x3	; count
	LDR R2, =0		; offset
	LDR R3, =0		; leadzero total
	
while
	TEQ R0, #0
	BEQ	endWhile
	LDR R5, [R4, R2, LSL #2]
	ADDS R0, R0, #0
	TEQ R5, #0
	BNE	notZero
	ADD R3, R3, #32
	ADD R2, R2, #1
	SUB R0, R0, #1
	B	while
notZero
	MOVS R5, R5, LSL #1		; LSL until carry flag is set
	BCS  endCLZ				;
	ADD R3, R3, #1			; while(!carryFlag)
	B notZero				; result =+ 1;
endCLZ

endWhile
	MOV R4, #96
	SUB R3, R4, R3
	

stop	B	stop

	AREA	TestData, DATA, READWRITE

ASize	DCD	3							 ; Number of elements in A
NUMBER	DCD	0x0,0x0000FFFF,0x0 ; Elements of A

	END
