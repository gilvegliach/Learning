.data
  
A: .byte 1 3 8 10
B: .byte 5 8 8 9 13
C: .byte 0 0 0 0 0 0 0 0 0 0 0 0
N: .byte 4
M: .byte 5
welcome: .asciiz "Dimostrazione del mergesort\n"


.text

main:
   li $v0, 4
   la $a0, welcome
   syscall           # print welcome message

   la $a0, A
   la $a1, B
   la $a2, C
   lb $a3, N

   addi $sp, -4     # allocate memory
   lb $t0, M
   sw $t0, 4($sp)   # 4($sp) <- M
   jal merge
  
   addi $sp, 4
   move $a0, $v0
   li   $v0, 1
   syscall         # print result

   li $v0, 10
   syscall           # exit

# merge(byte[] A, byte[] B, byte[] C,
#       byte   N, byte   M)
# rispettimamente in $a0-3 e 4($sp)

merge:
   lb   $t6, 4($sp)
   xor  $t0, $t0, $t0  # i = 0
   xor  $t1, $t1, $t1  # j = 0
   xor  $t2, $t2, $t2  # k = 0

merge_while1:
   bge $t0,  $a3, merge_end_while1  # while(i < N){
   add $t3,  $a0, $t0     # $t3 <- &A[i]
   lb  $t3,  ($t3)        # $t3 <-  A[i]

   add $t4,  $a1, $t1     # $t4 <- &B[j]
   lb  $t4,  ($t4)        # $t4 <-  B[j]

   add $t5,  $a2, $t2     # $t5 <- &C[k]
   
   bge $t3,  $t4, merge_else1      # if(A[i] < B [j]){
   sb  $t3,  ($t5)        # C[k] = A[i]
   addi $t0, 1            # i++
   addi $t2, 1            # k++
   b    merge_while1      # }
 
merge_else1:              # else {
   sb  $t4,  ($t5)        # C[k] = B[j]
   addi $t1, 1            # j++
   addi $t2, 1            # k++
   b    merge_while1      # }

merge_end_while1:         # } // end while

   
merge_while2:
   bge $t1,  $t6, merge_end_while2  # while(j < M){
 
   add $t4,  $a1, $t1     # $t4 <- &B[j]
   lb  $t4,  ($t4)        # $t4 <-  B[j]
   add $t5,  $a2, $t2     # $t5 <- &C[k]

   sb  $t4,  ($t5)        # C[k] = B[j]
   addi $t1, 1            # j++
   addi $t2, 1            # k++
   
   b merge_while2         # }

merge_end_while2:
   
   move $v0, $t2          # $v0 <- $t2
   jr   $ra   
