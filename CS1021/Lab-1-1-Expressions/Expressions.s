	AREA	Expressions, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =5	; x = 5
	LDR	R2, =6	; y = 6
	
	; Startin eq 3x^2 + y stored in R0
	LDR R3, =3 ; Load the value 3 in R3
	MUL R0, R1, R1 ; Square x
	MUL R0, R3, R0 ; Multiply 3 by x^2
	LDR R3, =5 ; Load 5 into R3
	MUL R3, R1, R3 ; Multiply 5 by x and store in R3
	ADD R0, R3, R0;
	
	; Starging eq 2x^2 + 6xy + 3y^2 stored in R4
	LDR R3, =2 ; load 2 into R3
	MUL R4, R1, R1 ; Square x
	MUL R4, R3, R4 ; Multiply x^2 by 2
	LDR R3, =6 ; load 6 into R3
	MUL R5, R3, R1 ; Multiply 6 and x
	MUL R5, R2, R5 ; Multiply 6x by y
	LDR R3, =3 ; load 3 into R3
	MUL R6, R2, R2 ; square y
	MUL R6, R3, R6 ; Multiply y^2 by 3
	ADD R4, R5, R4 ; Add 2x^2 and 6xy
	ADD R4, R6, R4
	
	; Starting eq x^3+6xy+8 stored in R5
	MUL R6, R1, R1 ; x^2 stored in R6
	MUL R7, R1, R6 ; (x^2)*x stored in R7
	LDR R8, =4 ; Load 4 into R8
	MUL R6, R8, R6 ; Multiply 4 by x^2
	MUL R3, R1, R3 ; Multiply 3 by x
	SUB R5, R7, R6 ; (x^3)-4(x^2) 
	ADD R5, R5, R3 ; ((x^3)-4(x^2))+3x
	LDR R8, =8 ; Load 8 into R8
	ADD R5, R5, R8 ; ((x^3)-4(x^2)+3x)+8
	
	
	
	
stop	B	stop

	END	