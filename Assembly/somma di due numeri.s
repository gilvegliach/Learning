.data
first: .asciiz "Insert first number"
second: .asciiz "Insert second number" 
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
la $a0, second 
syscall
li $v0, 5
syscall
move $s1, $v0
and $s1, $s1, 0x0000ffff    #lettura e salvataggio

li $v0, 4
la $a0, sum
syscall
li $v0, 1
add $a0, $s0, $s1          #somma 
syscall

li $v0, 10             #uscita
syscall

