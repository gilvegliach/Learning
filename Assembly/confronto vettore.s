.data
first: .asciiz "inserire dato"
vettore: .word 10, 20, 30, 40
lunghezza: .word 4
positivo: .asciiz "Il valore fa parte del vettore"
negativo: .asciiz "il valore non fa parte del vettore"

.text
main:

li $v0, 4
la $a0, first
syscall            #display della stringa first

li $v0, 5
syscall            #display del primo valore 

move $s0,$v0

li $s2, 0
la $t2, lunghezza
lw $s3, ($t2)
la $t1, vettore

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


