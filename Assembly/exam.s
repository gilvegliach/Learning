.data 
array: .word 1 2 7 4 5
len:   .word 5
pivot: .word 6

.text
main:
  la $a0, array
  lw $a1, len
  lw $a2, pivot

begin:
  beqz $a1, ret_0
  lw   $t0, ($a0)
  bgt  $t0, $a2, ret_1
  addi $a0, 4
  addi $a1, -1
  b begin
ret_0:
  li   $v0, 0
  b    end
ret_1:
  li   $v0, 1
end:
  move $a0, $v0
  li   $v0, 1
  syscall
  li   $v0, 10
  syscall
