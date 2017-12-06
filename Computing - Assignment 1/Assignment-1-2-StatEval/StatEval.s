	AREA	StatEval, CODE, READONLY
    IMPORT	main
    IMPORT	getkey
    IMPORT	sendchar
    IMPORT 	fputs
    EXPORT	start
    PRESERVE8

start
	LDR	R0,=hi				; load opening string
    BL	fputs				; output string to console
	
	MOV R0,#0				; intialise values 
	MOV R4,#0				;
	MOV R5,#0				;
	MOV R6,#0xA				;
	MOV R8,#0				; count
	MOV R9,#0				; sum 
	MOV R10,#0xFFFFFFFF		; min
	MOV R11,#0x0			; max
	MOV R12,#0				; mean


read 
	BL	getkey		; read key from console
	CMP	R0,#0x0D 	; while (key != esc)
	BEQ	storeNum	;
	CMP R0,#0x20	; if (key = Space || return)
	BEQ storeNum 	;		store number
	CMP R0,#0x1B	;
	BEQ stop		; 
	
	CMP R0,#0x39	; 	if (key isDigit)
	BGT read		; 	//i.e ASCII codes are from 0x30 to 0x39 inclusive
	CMP R0,#0x30	; 
	BLT read		;	{				
	BL	sendchar	; 		echo key back to console

	MOV R7,#0x30	;
	MOV R4,R0		;		store R0 -> R4
	SUB R4,R4,R7 	;		changes from ASCII code to value 
	MUL R5,R6,R5	; 		multiplies the value by ten if there is more than one digit
	ADD R5,R5,R4 	;		adds the newest value to the multiplied value 
					;	}
					; Final value stored in R5
	B	read		
	
storeNum
	BL sendchar
	ADD R8,R8,#1	; counter stored in R8	
	ADD R9,R9,R5	; sum stored in R9
	
	CMP R8,#1		; if (count == 1)
	BNE notFirst	;{
	MOV R10,R5		;	min = newValue	
	MOV R11,R5		;	max = newValue
notFirst			;}
	CMP R5,R10		;else if(R5<min)
	BLO newMin		;{	
	CMP R5,R11		;	newMin = R5 
	BHI newMax		;}	
newMin				;else if(R5>max)
	MOV R10,R5		;{
	B endMin		;	newMax = R5
newMax				;}
	MOV R11,R5
endMin
	MOV R4,#0
	MOV R5,R9
		
while				; 
	CMP R5,R8		;while (remainder >= divisor)
	BLO endWhile	;{
	ADD R4,R4,#1	;	quotient = quotient +1
	SUB R5,R5,R8    ;	reaminder = remainder - divisor
	B	while		;}
endWhile			;
	MOV R12,R4		; Move quotient to average
	
	SUB R7,R11,R10	; Range = max - min (extra mile)
	
	MOV R4,#0
	MOV R5,#0

	B   read

stop	B	stop

  AREA	MyStrings, DATA, READONLY

hi	DCB	"Input values and press space to store it. Press 'ESC' key to end program > ",0

        END
	
stop	B	stop

	END	
