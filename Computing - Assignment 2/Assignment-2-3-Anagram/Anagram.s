	AREA	Anagram, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =stringA
	LDR	R2, =stringB
	LDR R8, =0x9
	LDR R5, =0
	LDR R6, =0
	LDR R7, =0

lengthA 
	LDRB R3, [R1, R5]	;while(CharA != null)
	CMP R3, #0			;{
	BEQ endLengthA		;	if(CharA < 'A')
	CMP R3, #'A'		;	{
	BLO notCharA		;		lengthACount =+ 1;
	ADD R6 , R6, #1		;	}
notCharA				;	offset =+ 1;
	ADD R5, R5, #1		;}	
	B	lengthA			;
endLengthA				;
	MOV R5, #0			;offset = o;
	
lengthB 				; // repeats the process for stringB
	LDRB R3, [R2, R5]
	CMP R3, #0
	BEQ endLengthB
	CMP R3, #'A'
	BLO notCharB
	ADD R7 , R7, #1
notCharB
	ADD R5, R5, #1
	B	lengthB
endLengthB

	CMP R6, R7			; if(lengthACount != lengthBCount)
	BNE endScan			;	endScan

while
	LDRB R3,[R1]		; if(charA != upperCase)
	CMP R3, #'a'		;{	
	BLO notLowerCaseA	;	charA = charA - 0x20;		
	CMP R3, #'z'		;}	
	BHI notLowerCaseA 	;
	SUB R3, R3, #0x20	;
notLowerCaseA
	CMP R3, #0			;while (charA == null) 
	BEQ endwhile		;{
	MOV R5, #0			;	end program
scan					;
	LDRB R4,[R2, R5]	;	if(charB != upperCase)
	CMP R4, #'a'		;	{			
	BLO notLowerCaseB	;		charB = charB - 0x20;
	CMP R4, #'z'		;	}	
	BHI notLowerCaseB 	;
	SUB R4, R4, #0x20
notLowerCaseB
	CMP R4, #0			;	if(charB == null)
	BEQ endScan			;		endScan
	CMP R3, R4			;	if(charB == charA)
	BEQ pair			;	{
	ADD R5, R5, #1		;		CharA = null ; //cross-out method 
	B	scan			;		CharB = null ;
						;		CharA Address =+ 1 ;
pair					;	}
	STRB R8, [R1]		;	else
	STRB R8, [R2, R5]	;	{
	ADD R1, R1, #1		;		CharB Address =+ 1 ;
	B	while			;	}
						;}
endScan
	MOV R0, #0
	B	stop
	
endwhile
	MOV R0, #1

stop	B	stop

	AREA	TestData, DATA, READWRITE

stringA	DCB	"I THINK therefore I AM.",0			; Handles phrase anagrams with different cases
stringB	DCB	"I FEAR to think I'm HERE.",0
	
	END
