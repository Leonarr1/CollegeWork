	AREA	Val2Dec, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R4, =7890
	LDR	R5, =decstr
	LDR R1, = 0				; a (remainder)
	LDR R2, = 10			; b
	LDR R3, = 0				; answer
	LDR R6, = 0     		; char count
	
	MOV R1, R4
	
while
	CMP R1, #10
	BLO endwhile
	BL	divide				;call divide
	STR R1, [SP, #-4]!
	MOV R1, R3
	MOV R3, #0
	ADD R6, R6, #1
	B while
endwhile
	STR R1, [SP, #-4]!
	ADD R6, R6, #1
whiRead
	CMP R6, #0 
	BEQ endRead
	LDR R1, [SP], #4
	ADD R1, R1, #0x30
	STRB R1, [R5], #1
	SUB R6, R6, #1
	B whiRead
endRead
	
stop	B	stop

; divide subroutine
divide 
	CMP R1, R2
	BLO endWhile
	SUB R1, R1, R2
	ADD R3, R3, #1
	B divide				
endWhile
	BX lr				; return from subroutine
	
	
	
	AREA	TestString, DATA, READWRITE

decstr	SPACE	16

	END	