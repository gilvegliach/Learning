.data

matrice1: .word 10, 20, 30, 40
matrice2: .word 4, 8, 20, 16
risultato: .word 0
dimensione1: .word 2
dimensione2: .word 2

.text
main:

la $t1, matrice1
la $t2, matrice2
la $t3, risultato
la $t4, dimensione1
la $t5, dimensione2
lw $s3, $t4
lw $s4, $t5
mul $s5, $s3, $s4


confronto:
lw $s1, ($t1)

beq $s0 $s1 ok
beq $s2 $s3 no
addi $s2 1
addi $t1 4
j confronto

ok:
li $v0, 4
la $a0, positivo
syscall

li $v0, 10             #uscita
syscall


no:
li $v0, 4
la $a0, negativo
syscall

li $v0, 10             #uscita
syscall


