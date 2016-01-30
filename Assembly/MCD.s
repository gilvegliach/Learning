.data
first: .asciiz "inserire primo numero "
second: .asciiz "inserire secondo numero " 
risultato: .asciiz "il massimo comun divisore è: "

.text
main:

li $v0, 4
la $a0, first
syscall            
li $v0, 5
syscall           
move $s0,$v0


li $v0, 4
la $a0, second 
syscall
li $v0, 5
syscall
move $s1, $v0

jal euclide

li $v0, 4
la $a0, risultato
syscall
li $v0 1
move $a0, $s0 
syscall


li $v0, 10             #uscita
syscall

euclide:
addi $sp, $sp -4
sw $ra, 0($sp)
bgt $s0, $s1, casoa
bgt $s1, $s0, casob
j ritorno

casoa:
sub $s0, $s0, $s1
jal euclide
j ritorno

casob:
sub $s1, $s1, $s0
jal euclide

ritorno:
lw $ra, 0($sp)
addi $sp, $sp, 4
jr $ra