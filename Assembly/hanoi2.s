.data
first: .asciiz "numero dischi "
muovi: .asciiz "muovi il disco "
da: .asciiz " dal perno "
a: .asciiz " al perno "

.text
main:

li $v0, 4             #primo dato: numero dischi
la $a0, first
syscall                   
li $v0, 5
syscall


move $s0 $v0   #numero dischi da spostare                   
li $s1 1       #perno di partenza      
li $s2 2       #perno di destinaizone
li $s3 3       #perno temporaneo

jal torre

li $v0, 10                #uscita
syscall


torre:
sub $sp 20
sw $ra, 0($sp)
sw $s0, 4($sp)
sw $s1, 8($sp)
sw $s2, 12($sp)
sw $s3, 16($sp)
bgt $s1 1 ricorsione

li $v0, 4
la $a0, muovi
syscall
move $a0 $s0
li $v0, 1
syscall

li $v0, 4
la $a0, da
syscall
move $a0, $s1
li $v0, 1
syscall

li $v0, 4
la $a0, a
syscall
move $a0, $s2
li $v0, 1
syscall
jr $ra

addi $s0, $s0, -1
lw $s1, 16($sp)
lw $s2, 12($sp)
lw $s3, 8($sp)
jal torre

ricorsione:
addi $s0 $s0 -1
lw $s1 8($sp)
lw $s2 16($sp)
lw $s3 12($sp)
jal torre 

addi $s0, $s0, -1
lw $s1, 16($sp)
lw $s2, 12($sp)
lw $s3, 8($sp)
jal torre


ritorno:
addi $sp $sp 20
lw $ra, 0($sp)
lw $s0, 4($sp)
lw $s1, 8($sp)
lw $s2, 12($sp)
lw $s3, 16($sp)

li $v0, 4
la $a0, muovi
syscall
move $a0, $s0
li $v0, 1
syscall

li $v0, 4
la $a0, da
syscall
move $a0, $s1
li $v0, 1
syscall

li $v0, 4
la $a0, a
syscall
move $a0, $s2
li $v0, 1
syscall

jr $ra

