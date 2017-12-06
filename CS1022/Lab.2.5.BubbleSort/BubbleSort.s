	AREA	BubbleSort, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R4, =array	; address of array
	LDR R6, =1		; swapped = true
	


swapped							;while(swapped)
	CMP R6, #0					;{	
	BEQ notSwapped				;
	MOV R6, #0					; swapped = false;
	LDR R7, =0					; i = 0;
	LDR	R5, =arrayN				; R5 = address of array size
	LDR	R5, [R5]				; R5 = array size
while							;
	CMP R5, #1					; for(i = 0; i<N; i++)
	BEQ endwhile				; {
	LDR R8, [R4, R7, LSL #2]	;		R8 = array[i];
	ADD R9, R7, #1				;		R9 = i + i;
	LDR R10, [R4, R9, LSL #2]	;		R10 = array[i+1];
	CMP R8, R10					;		if( R8 > R10)
	BLO isLess					;		{
	MOV R0, R4					;		(move parameters to interface)
	MOV R1, R7					;		
	MOV R2, R9					;		
	BL	swap					;			swap(array, i, i+1);
	MOV R6, #1					;			swapped = true;
isLess							;		}
	ADD R7, R7, #1				;		i ++;
	SUB R5, R5, #1				;		
	B while						; }
endwhile						;
	B swapped					;}
notSwapped



stop	B	stop
					

; interface 
; R0 = array address
; R1 = i ( index of first value to swap )
; R2 = j ( index of second value to swap )
										

swap										; swap subroutine
	STMFD sp!, {R4-R5,lr} 					; store LR and registers R4 - R5  
	LDR R4, [R0, R1, LSL #2]				; R4 = array[i]
	LDR R5, [R0, R2, LSL #2]				; R5 = array[j]
	STR R4,	[R0, R2, LSL #2]				; array[j] = R4 
	STR R5, [R0, R1, LSL #2]				; array[i] = R5
	LDMFD sp!,{R4-R5,pc}					; return and restore Registers




	AREA	TestArray, DATA, READWRITE

; Array Size
arrayN	DCD	15

; Array Elements
array	DCD	33,17,18,92,49,28,78,75,22,13,19,13,8,44,35

	END	