.data
first: .asciiz "inserire numero di elementi"
second: .asciiz "inserire numero di disposizioni"
fatt: .asciiz "le combinazioni possibili sono: "

.text
main:

li $v0, 4
la $a0, first
syscall            #display della stringa first

li $v0, 5
syscall            #display del primo valore 

move $s0,$v0
#and $s0, $s0, 0x0000ffff

li $v0, 4
la $a0, second
syscall
li $v0, 5
syscall

move $s4, $v0

li $v0, 4
la $a0, fatt
syscall

li $v0, 1
#li $s2 0x00000001
li $s3 0
sub $s4 $s0 $s4

fattoriale:
beq $s0 $s4 fine
sub $s1 $s0 $s3
mul $s2 $s2 $s1
addi $s3 $s3 1
addi $s4 $s4 1
j fattoriale

fine:
move $a0 $s2
syscall

li $v0, 10             #uscita
syscall

