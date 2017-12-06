
 AREA	DisplayResult, CODE, READONLY
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
	CMP R0,#0x20	; if(lastInput = space)
	BEQ secondSpace	;	branch beyond store values
	BL	getkey		; read key from console
	CMP	R0,#0x0D 	; while (key != CR
	BEQ	storeNum	;
	CMP R0,#0x20	; AND key != Space)
	BEQ storeNum 	;
	B	validInput	;

secondSpace			;
	BL  getkey		; if(key = 'esc')
	CMP R0,#0x1B	;
	BEQ meanDisplay	; end program and display mean
validInput	
	CMP R0,#0x39	; 	if (key isDigit)
	BGT read		; 	//i.e ASCII codes are from 0x30 to 0x39 inclusive
	CMP R0,#0x30	; 
	BLT read		;	{				
	BL	sendchar	; 		echo key back to console
 	
	MOV R7,#0x30	;
	MOV R4,R0		;		store R0 -> R4
	RSB R4,R7,R4 	;		changes from ASCII code to value 
	MUL R5,R6,R5	; 		multiplies the value by ten if there is more than one digit
	ADD R5,R5,R4 	;		adds the newest value to the multiplied value 
					;	}
					; Final value stored in R5
	B	read		
	
storeNum
	BL sendchar

	ADD R8,R8,#1	; counter stored in R8	
	ADD R9,R9,R5	; sum stored in R9
	
	CMP R8,#1		;if(count=1)
	BNE	notFirst	;{
	MOV	R10,R5		;min = input
	MOV R11,R5		;max = input
notFirst			;}	
	
	CMP R5,R10		;if(R5<min)
	BLO newMin		;{	
	CMP R5,R11		;	newMin = R5 
	BHI newMax		;}	
newMin				;else if(R5>max)
	MOV R10,R5		;{
	B endMin		;	newMax = R5
newMax				;}
	MOV R11,R5
endMin

	SUB R7,R11,R10	; Range

	MOV R5,#0		; clear the registers to take new inputs
	MOV R4,#0		;
	CMP R0,#0x20
	BEQ secondSpace
	B   read
	
meanDisplay			
	MOV R4,#0		;clear R4 for use as quotient
	MOV R5,R9		;move sum into R5 
	MOV R6,#100		;
	MUL	R5,R6,R5	;mean =*10;  //used to compute average
while				; 
	CMP R5,R8		;while (remainder >= divisor)
	BLO endWhile	;{
	ADD R4,R4,#1	;	quotient = quotient +1
	SUB R5,R5,R8    ;	reaminder = remainder - divisor
	B	while		;}
endWhile			;
	MOV R12,R4		; Move quotient to average


	MOV	R0,#0xA		;	print(\n) // movew to new line to display mean 
	BL	sendchar	;
	MOV R4,#1		; result = 1
	MOV R5,#0		; clear register for use in division
	MOV R6,#10		; used to compute powers of ten
powers
	CMP R4,R12		;while(Mean > powers)
	BHS endPowers	;{
	MUL R4,R6,R4	; result = result*x
	B	powers		;}
endPowers

display
	CMP R4,#100		;if(digit place == hundreds)
	BNE deci		;{
	MOV R0,#0x2E	;	print(".")	//decimal place
	BL	sendchar	;}
deci				;
	CMP R4,#1		;if(digit place == ones)
	BLS endDisplay	;	end display
divPower			;
	CMP R4,#10		;while(remainder >= 10)
	BLO endDivPower	;{
	SUB R4,R4,#10	;	remainder = remainder - 10;
	ADD R5,R5,#1	;	quotient = quotient + 1;
	B	divPower	;}
endDivPower			;
	MOV R6,#0		;	clear quotient register
whiMean				;
	CMP R12,R5		;while(mean >= divisor)
	BLO endWhiMean	;{
	ADD R6,R6,#1	; 	quotient = quotient + 1
	SUB R12,R12,R5	;	mean = mean - divisor
	B	whiMean		;}
endWhiMean			;
	ADD R6,R6,#0x30	; convert digit to ascii code
	MOV R0,R6		; move to R0
	CMP R0,#0x30	;if(ascii == 0)
	BNE noZero		;{
	CMP R5,#1		;	if(divisor < 1)	//eliminates leading zeros
	BLO display		;		don't print
noZero				;}
	BL  sendchar	;else
	MOV R4,R5		;{
	MOV R5,#0		;	print digit
	B	display		;}
endDisplay			;
	LDR R0,=mn		; load string 
	BL  fputs		; output to console
stop	B	stop

  AREA	MyStrings, DATA, READONLY

hi	DCB	"Input values and press space to store it. Press 'ESC' key to end program.\r\n",0
mn  DCB " is the mean",0 
     
	 END
	
stop	B	stop

	END	