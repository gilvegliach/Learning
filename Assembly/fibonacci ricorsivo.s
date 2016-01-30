.data
insert: .asciiz "Inserici il numero: "
result1: .asciiz "F("
result2: .asciiz ")="
newline: .asciiz "\n"

.text
main:
li $v0, 4
la $a0, insert
syscall				# stampa richiesta argomento
	
li $v0, 5
syscall				# legge l'argomento
			
move $s0, $v0		# salva il numero dato in ingresso

li $v0, 4
la $a0, result1
syscall					# stampa result1

li $v0, 1
move $a0, $s0 
syscall					# stampa l'argomento della funzione

li $v0, 4
la $a0, result2
syscall					# stampa result2

move $a0, $s0 
sub $sp, $sp 4			# alloca una word nello stack
sw $ra, ($sp)			# salva $ra nello stack
jal fibonacci			# chiama la funzione di fibonacci

move $s0, $v0			#salva il risultato

li $v0, 1
move $a0, $s0 
syscall					# stampa il risultato di fibonacci

li $v0, 4
la $a0, newline
syscall					# va a capo

lw $ra,($sp)     		# ripristina il return address
addi $sp, $sp, 4		# dealloca il frame
jr $ra					# esce dal programma

fibonacci:
sub $sp, $sp, 12		# alloca tre word nello stack
sw $ra, ($sp)			# salva $ra nello stack
sw $a0, 4($sp)			# salva $a0 nello stack
sw $s0, 8($sp)			# salva $s0 nello stack (necessario perche' usato all'interno della procedura).

li $v0,0			
beqz $a0,return			# caso base: F(0)=0

li $v0, 1			
beq $a0,$v0,return		# caso base: F(1)=1

sub $a0, $a0, 1			# $a0=n-1
jal fibonacci			# $v0=F(n-1)
move $s0, $v0 			# $s0=F(n-1)


lw $a0, 4($sp) 			# ripristina il vecchio a0
sub $a0, 2
jal fibonacci			# $v0=F(n-2)
add $v0,$v0,$s0			# F(n)=F(n-1)+F(n-2)

return:
lw $ra, ($sp)			# ripristina il return address
lw $s0, 8($sp)			# ripristina $s0
addi $sp, $sp 12		# dealloca il frame
jr $ra					# ritorna