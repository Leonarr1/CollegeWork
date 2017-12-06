 AREA	DisplayResult, CODE, READONLY
    IMPORT	main
    IMPORT	getkey
    IMPORT	sendchar
    IMPORT 	fputs
    EXPORT	start
    PRESERVE8

start
menuDisplay
	MOV R3,#1	
	MOV R5,#0
powers
	CMP R3,R12		;while(Mean < powers)
	BHS endPowers	;{
	MUL R3,R1,R3	; result = result*x
	B	powers		;}
endPowers

display
	CMP R12,#0
	BLS endDisplay
divPower
	CMP R3,#10		;while(remainder >= 10)
	BLO endDivPower	;{
	SUB R3,R3,#10	;	remainder = remainder - 10;
	ADD R5,R5,#1	;	quotient = quotient + 1;
	B	divPower	;}
endDivPower
	MOV R6,#0
	
whiMean
	CMP R12,R5		;while(mean >= divisor)
	BLO endWhiMean	;{
	ADD R6,R6,#1	; 	quotient = quotient + 1
	SUB R12,R12,R5	;	mean = mean - divisor
	B	whiMean	;}
endWhiMean
	ADD R6,R6,#0x30
	MOV R0,R6
	BL  sendchar
	MOV R3,R5
	MOV R5,#0
	B	display
endDisplay

stop	B	stop

	END	