.data
first: .asciiz "Insert number"
sum: .asciiz "The sum is: "

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
la $a0, sum
syscall
li $v0, 1
addi $s1, $s0, 1
li $s2, 0x00000002
mul $s0, $s0, $s1
div $a0, $s0, $s2          #somma 
syscall

li $v0, 10             #uscita
syscall

