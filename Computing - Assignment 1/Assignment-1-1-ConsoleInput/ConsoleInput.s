	AREA	ConsoleInput, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	IMPORT  fputs
	EXPORT	start
	PRESERVE8

start

	LDR	R0,=hi
    LDR	R1,=0
    BL	fputs
	MOV R0,#0
	MOV R4,#0
	MOV R5,#0
	MOV R6,#0xA
	MOV R7,#0x30
read
   	BL	getkey		; read key from console
	CMP	R0,#0x0D 	; while (key != CR)
	BEQ	endRead		;{
	CMP R0,#0x39	; 	if (key isDigit)
	BGT read		; 	//i.e ASCII codes are from 0x30 to 0x39 inclusive
	CMP R0,#0x30	; 		key<0x39 && key>0x30
	BLT read		;	{				
	BL	sendchar	; 		
					;
					;
	MOV R4,R0		;		store R0 -> R4
	RSB R4,R7,R4 	;		changes from ASCII code to value 
	MUL R5,R6,R5	; 		multiplies the value by ten if there is more than one digit
	ADD R5,R5,R4 	;		adds the newest value to the multiplied value 
					;	}
					;}
					; Final value stored in R5
	B	read		;
			
endRead
	
	MOV R4,R5

stop	B	stop


        AREA	MyStrings, DATA, READONLY
hi	DCB	"Input values and press return to store it> ",0

        END
	
stop	B	stop

	END	