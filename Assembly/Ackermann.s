.data
first: .asciiz "inserire il primo valore"
second: .asciiz "inserire il secondo valore"
ack: .asciiz "il valore corrispondente nella successione di Ackermann è"

.text
main:

li $v0, 4
la $a0, first
syscall            #display della stringa first

li $v0, 5
syscall            #display del primo valore 

move $s0, $v0

li $v0, 4
la $a0, second
syscall            #display della stringa second

li $v0, 5
syscall            #display del secondo valore 

move $s1, $v0

jal ackermann

li $v0, 4
la $a0, ack
syscall     

li $v0 1
move $a0, $s1
syscall

li $v0, 10             #uscita
syscall

ackermann:
sub $sp 12
sw $ra 8($sp)
sw $s0 4($sp)
sw $s1 0($sp)
bgtz $s0 passo2
addi $s1 $s1 1
b return

passo2:
bgtz $s1 ricorsione
addi $s0 $s0 -1
li $s1 1
jal ackermann
b coda

ricorsione:
addi $s0 $s0 -1
jal ackermann

coda:
addi $s0 $s0 -1
jal ackermann
lw $ra 8($sp)

return:
add $sp 12
jr $ra




