.data
    newline: .asciiz "\n"
    to     : .asciiz "->"
    welcome: .asciiz "Torre di hanoi per n\nn=? \n"

.text
main:
    li   $v0, 4
    la   $a0, welcome
    syscall              # welcome string

    li   $v0, 5
    syscall              # read number in $v0
    
    
    move $a0, $v0        
    li   $a1, 1
    li   $a2, 2
    li   $a3, 3
    jal hanoi

    li   $v0, 10         # exit
    syscall

hanoi:
    addi $sp, -20
    sw   $ra, 16($sp)
    sw   $a0, 12($sp)
    sw   $a1, 8($sp)
    sw   $a2, 4($sp)
    sw   $a3, 0($sp)
    
    
    
    beq  $a0, $0, base_case
    addi $a0, -1
                         # swap optimized
    sub  $a3, $a2, $a3   # a3=a2-a3  
    sub  $a2, $a2, $a3   # a2=a2-(a2-a3)=a3
    add  $a3, $a2, $a3   # a3=(a2-a3)+a3

    jal hanoi            # first recursion
 
    li   $v0, 1
    move $a0,  $a1
    syscall              # print first peow
    
    li   $v0, 4
    la   $a0, to
    syscall              # print "->"
    
    li   $v0, 1
    move $a0, $a3        # second peow is a3
    syscall              # print second peow
    
    li   $v0, 4
    la   $a0, newline
    syscall              # print "\n"

    lw   $a0, 12($sp)
    addi $a0, -1
    lw   $a1, 0($sp)       # invert first and third peow
    lw   $a2, 4($sp)
    lw   $a3, 8($sp)
    jal  hanoi

    
base_case:     #nop
    
    lw   $ra, 16($sp)
    lw   $a0, 12($sp)
    lw   $a1, 8($sp)
    lw   $a2, 4($sp)
    lw   $a3, 0($sp)
    addi $sp, 20			
    jr   $ra

