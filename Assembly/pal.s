.data
    string:   .space 1024
    welcome:  .asciiz "Inserire una stringa:\n"
    pal_str:  .asciiz "String is palindrome!\n"
    npal_str: .asciiz "String is not palindrome :(\n"
.text
main:
#initial welcome :D
    li  $v0, 4
    la  $a0, welcome
    syscall

#read a string
    li  $v0, 8
    la  $a0, string
    li  $a1, 1024
    syscall

#calculate length in $s0
    xor  $s0, $s0, $s0
length:
    lb   $t0, string($s0)
    beqz  $t0, end_length
    addi $s0, 1
    b    length
end_length:    
    addi $s0, -2  #remove the 0 terminator and start from 0

#palindrome
    xor $s1, $s1, $s1       #$s1 is the first counter
 
check_pal: 
    beq $s0, $s1, pal       #is palindrome if the index are the same
    lb  $t1, string($s1)    #first char
    lb  $t0, string($s0)    #last char
    bne $t1, $t0, not_pal
    b   check_pal

#when is pal print a nice message
pal:
    li  $v0, 4
    la  $a0, pal_str
    syscall
    b end

not_pal:
    li  $v0, 4
    la  $a0, npal_str
    syscall
    b end
        
#exit
    li  $v0, 10
    syscall

