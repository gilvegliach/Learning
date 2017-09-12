.data
QUEST: .asciiz "Inserisci una cifra: "
RIS: .asciiz "La somma e': "

.text
main:
li $v0, 4                #Domanda
la $a0, QUEST
syscall
li $v0, 5
syscall                         #input

move $s0, $v0
move $s1, $v0
addi $s1, -1

#li $v0, 4                #stringa di risposta
#la $a0, RIS
#syscall

SUM:                    #ricorsione

add $s0, $s0, $s1       
addi $s1, $s1, -1
bnez $s1, SUM           #condizione di uscita

move $a0, $s0
 
li $v0, 1
syscall

li $v0, 10
syscall
