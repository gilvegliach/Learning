	.data
#mesg:	.word 1, 2, 3, 4,5,6,7,8,9, 0x0040003c 
mesg:   .word 0x3402000a, 0x0000000c, 0, 0,0,0,0,0,0, 0x7fffef54

strlen: .byte   48

	.text
	.globl main
##
## main
##
main:
	sub $sp, 4       # push the return address on the stack
	sw $ra, 0($sp)

	jal foobar       # exec the "foobar" function

	li $v0, 4        # print the message
	la $a0, mesg
	syscall

	lw $ra, 0($sp)   # pop the return address from the stack and return
	add $sp, 4
	jr $ra
        li $v0, 10
        syscall

##
## foobar
##
foobar:
	sub $sp, 40      # push $ra on the stack
	sw $ra, 36($sp)

	li $t0, 0        # copy mesg to a local buffer
	move $t1, $sp
	lb $t3, strlen
	sub $t3, 1
copy_loop:
	lb $t2, mesg($t0)
	sb $t2, 0($t1)
	beq $t0,$t3 foobar_exit
	add $t0, 1
	add $t1, 1
	b copy_loop
foobar_exit:
	lw $ra, 36($sp)  # pop $ra from the stack and return
	add $sp, 40 
	jr $ra
