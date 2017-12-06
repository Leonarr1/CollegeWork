
	AREA SymmDiff, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	LDR R0,=ASize
	LDR R0, [R0]
	LDR R1,=AElems
	LDR R2,=BSize
	LDR R2, [R2]
	LDR R3,=BElems
	LDR R11,=CSize
	LDR R5,=CElems

	
	LDR R6,=0
	LDR R7,=0			; memory increment
	LDR R8,=0			; count for Csize
	LDR R9,=0			; zero constant
	LDR R12,=0			; scanCount
	
whileA
	MOV R7, #0			;
	CMP R0, #0			;while(sizeA>0)
	BLS endWhileA		;{
	SUB R0, R0, #1		;	sizeA--;
	LDR R4, [R1]		;	R4 = char.AElemsAddress
scanB					;
	CMP R12, R2			;	while(count < Bsize)
	BHS endScanB		;	{
	LDR R6, [R3, R7]	;		R6 = Char.BElemsAddress, offset by R7 // R7 starts at zero
	CMP R4, R6			;   	if( R4 != R6 )
	BNE	notPairA		;		{ 
	B	pairA			;			;count++;
						;			;offset =+ 4;
notPairA				;		}
	ADD R12, R12, #1	;		else
	ADD R7, R7, #4		;		{
	B	scanB			;			AElemsAddress =+ 4;
						;			count = 0;
endScanB				;			break; // break to start of while loop
	STR R4, [R5]		;		}
	ADD R5, R5, #4		;	}
	ADD R8, R8, #1		;	CElems[R5] = CharA 
pairA					;	R5 =+ 4;
	ADD R1, R1, #4		;	CSize =+ 1;
	MOV R12, #0			;}
	B	whileA	
endWhileA

	LDR R0,=ASize		; reloads the original addresses and set sizes
	LDR R0, [R0]
	LDR R1,=AElems
	LDR R2,=BSize
	LDR R2, [R2]
	LDR R3,=BElems
	
whileB					; begins the scan again but this time scans Set A using the values from Set B
	MOV R7, #0
	CMP R2, #0
	BLS endWhileB
	SUB R2, R2, #1
	LDR R4, [R3]
scanA
	CMP R12, R0
	BHS endScanA
	LDR R6, [R1, R7]
	CMP R4, R6
	BNE	notPairB

	B	pairB
		
notPairB
	ADD R12, R12, #1
	ADD R7, R7, #4
	B	scanA

endScanA
	STR R4, [R5]
	ADD R5, R5, #4
	ADD R8, R8, #1		; Count for CSize
pairB
	ADD R3, R3, #4
	MOV R12, #0
	B	whileB	
endWhileB

	STR R8, [R11]


stop	B	stop


	AREA	TestData, DATA, READWRITE

ASize	DCD	8					; Number of elements in A
AElems	DCD	4,6,2,13,19,7,1,3	; Elements of A

BSize	DCD	6					; Number of elements in B
BElems	DCD	13,9,1,20,5,8		; Elements of B

CSize	DCD	0					; Number of elements in C
CElems	SPACE	56				; Elements of C

	END