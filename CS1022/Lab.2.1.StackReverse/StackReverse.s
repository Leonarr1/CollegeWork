	AREA	StackReverse, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	LDR	R5, =string
	LDR R4,=0
	
while	
	LDRB R6, [R5], #1					;pop R5 in R6
	CMP  R6, #0							;while ( R6 != 0)
	BEQ  endWhi							;{
	STRB R6, [SP], #1					;		push R6 to SP
	B	 while
endWhi									;}
	LDR	R5, =string						;R5 = string		
whileSTR		
	LDRB R6,[SP, #-1]!					;pop from R6 
	CMP  R6, #0							;while (R6 != 0)
	BEQ  endSTR							;{
	STRB R6,[R5], #1					;		push R6 to R5
	B	whileSTR						;}
endSTR

	
stop	B	stop


	AREA	TestString, DATA, READWRITE

string	DCB	"abcdef"

	END	