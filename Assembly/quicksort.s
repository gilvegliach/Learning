.data
   A: .word 3 23 521 1 32 5 3
   L: .word 7

.text
main:
   lb $a0, 0
   lw $a1, L
   jal quicksort


quicksort:
   addi $sp, 8
   sw   $a0,  ($sp)
   sw   $a1, 4($sp)

   

# p -> a0 
# r -> a1 

partition:
    la $t0, A
    add $t0, $a0, $t0 # t0 <-  &A[p]
    lb $t0, ($t0)     # t0 <-  x = A[p]
    addi $t1, $a0, 1  # t1 <-  i = p+1
    move $t2, $a1     # t2 <-  j = r
    
begin_while1:
    bgt  $t2, $t1, end_while1   # while(i<=j){   
    


end_while1:	
