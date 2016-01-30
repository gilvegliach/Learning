.data
board: .byte 0, 0, 0, 0, 0, 0, 0
       .byte 0, 0, 0, 0, 0, 0, 0
       .byte 0, 0, 0, 0, 0, 0, 0
       .byte 0, 0, 0, 0, 0, 0, 0
       .byte 0, 0, 0, 0, 0, 0, 0
       .byte 0, 0, 0, 0, 0, 0, 0
newline: .asciiz "\n"
.globl main
.text
print_board:
	li  $t0, 6   # indice colonne
	li  $t1, 7   # indice righe

        outer_loop:
	beqz $t0, end_outer_loop
        
	li  $t1, 7
        inner_loop:
	beqz $t1, end_inner_loop
	li  $t3, 6 
        mul $t3, $t0, $t3
	add $t3, $t3, $t1    # t3 = 6*t0+t1
	lb  $t3, board($t3)  # board[t3]
	li  $v0, 1
	move $a0, $t3
	syscall              # print the byte
	addi $t1, -1
	b inner_loop
	
	li $v0, 4
	la $a0, newline
	syscall
	
	end_inner_loop:
      
        addi $t0, -1
	b outer_loop
        end_outer_loop:

        jr $ra

start:
       jal print_board
       li $v0, 10
       syscall    #exit

