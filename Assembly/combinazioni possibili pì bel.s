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
and $s0, $s0, 0x0000ffff

li $v0, 4
la $a0, second
syscall
li $v0, 5
syscall

move $s3, $v0

li $v0, 4
la $a0, fatt
syscall

li $v0, 1
li $s2, 0x00000001
sub $s3, $s0, $s3
addi $s3, $s3, 1

fattoriale:
bgt $s3, $s0, fine
mul $s2, $s2, $s3
addi $s3, $s3, 1

j fattoriale

fine:
move $a0, $s2
syscall

li $v0, 10             #uscita
syscall

