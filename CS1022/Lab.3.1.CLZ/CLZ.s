	AREA	CLZ, CODE, READONLY
	IMPORT	main
	EXPORT	start

	AREA	Undef, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	r4, =0x40000024		; 0x40000024 is mapped to 0x00000024
	LDR	r5, =UndefHandler 	; Address of new undefined handler
	STR	r5, [r4]			; Store new undef handler address
	
	;
	; Test our new instruction
	;
	LDR	r5, =2_00000000000000010000000000100000	;	(15 (0xF) leading zeros)	will be stored in R4
	; This is our undefinied unstruction opcode
	MOV R1, #0x4			
	MOV R2, #0x4	
	SUBS R0, R1, R2			; simple instruction to set condition flag

	DCD	0x016F4F15	; CLZ r4, r5 (r0 = leading zeros in R5)
		
	; condition set to EQ (if the Z flag is set the operation will execute)
	; otherwise the condtion will be skipped
	; assembler is handling the condition, however to do this manually I would isolate the condition flags and ensure the correct flag was set/clear
		
stop	B	stop	


;
; Undefined exception handler
;
UndefHandler
	STMFD	sp!, {r0-r12, LR}	; save registers

	LDR	r4, [lr, #-4]			; load undefinied instruction
	LDR r9, =0xF000F00F
	BIC	r5, r4, r9	; clear all but opcode bits
	LDR R10, =0x016F0F10
	TEQ	r5, R10					; check for undefined opcode 0x1
	BNE	endif1					; if (clz instruction) {
	BIC	r5, r4, #0xFFFFFFF0		;  isolate Rm register number
	BIC	r7, r4, #0xFFFF0FFF		;  isolate Rd register number
	MOV	r7, r7, LSR #12			;
	LDR	r1, [sp, r5, LSL #2]	;  grab saved Rm off stack
	BL	countLZ
	STR	r0, [sp, r7, LSL #2]	;  save result over saved Rd		
endif1					; }
	LDMFD	sp!, {r0-r12, PC}^	; restore register and CPSR


; CLZ subroutine
; returns amount of leading zeros
; paramaters: r0: result (variable)
;             r1: (value)
countLZ
	STMFD	sp!, {r1-r2,lr}	; save registers
	MOV R0, #0				; set R0 to zero
	CMP R1, #0				; check if zero
	BNE notZero
	MOV R0, #32				; if(value = zero)
	B	endSub				; 	set result = 32
notZero		
	MOVS R1, R1, LSR #1		; LSL until carry flag is set
	BCS  endSub				;
	ADD R0, R0, #1			; while(!carryFlag)
	B notZero				; result =+ 1;
endSub						;

	LDMFD	sp!, {r1-r2, pc} ; restore registers and return


	END


	END	
	