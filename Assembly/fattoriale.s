.data
first: .asciiz "inserire numero" 
fatt: .asciiz "il fattoriale è: "

.text
main:

li $v0, 4
la $a0, first
syscall            #display della stringa first

li $v0, 5
syscall            #display del primo valore 

move $s0, $v0

li $v0, 4
la $a0, fatt
syscall
li $v0, 1
li $s3, 0
li $s2, 1

fattoriale:
beq $s0, $s3, fine
sub $s1, $s0, $s3
mul $s2, $s2, $s1
addi $s3, $s3, 1
j fattoriale

fine:
move $a0, $s2
syscall

li $v0, 10             #uscita
syscall

