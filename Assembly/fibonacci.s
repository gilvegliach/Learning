.data
first: .asciiz "inserire numero" 
fib: .asciiz "il valore corrispondente nella successione di Fibonacci è"

.text
main:

li $v0, 4
la $a0, first
syscall            #display della stringa first

li $v0, 5
syscall            #display del primo valore 

move $s0, $v0

li $v0, 4
la $a0, fib
syscall

li $s1, 0
li $s2, 1
li $s3, 0
beq $s0, $s1, fine
li $s3, 1
beq $s0, $s2, fine
li $s4 1

ricorsione:
add $s3, $s1, $s2
move $s1, $s2
move $s2, $s3
addi $s0, $s0, -1
bgt $s0, $s4 ricorsione


fine:
li $v0 1
move $a0, $s3
syscall

li $v0, 10             #uscita
syscall

