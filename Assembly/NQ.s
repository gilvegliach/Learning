# Gil Vegliach e Karrie Moore  (Gruppo 1 - esercizio 2)
# Matricole s80568 e s81152
# Gil.Vegliach@gmail.com e KarrieKimMoore@msn.com

.data
req: .asciiz "Problema delle N-Regine\n------------------------\nInserisci un N: "
no_solution: .asciiz "Non ci sono soluzioni"
empty_cell: .asciiz "[ ]"
queen_cell: .asciiz "[M]"
rtrn: .asciiz "\n"

.text
main:
	li $v0, 4
	la $a0, req
	syscall				# stampa richiesta argomento
	
	li   $v0, 5
	syscall				# legge l'argomento
	move $s0, $v0			# $s0 = N
	
						# Gestione input
	li $t0, 1				# Caso N=1
	bgt $s0, $t0, greater_than1 
	li  $v0, 4
	la $a0, queen_cell		# Stampa l'unica soluzione
	syscall
	b exit
	
greater_than1:
	li $t0, 3
	bgt $s0, $t0, greater_than3
	li $v0, 4
	la $a0, no_solution
	syscall
	b exit
	
greater_than3:
     move $a0, $s0			
	li 	$v0, 9
	syscall				# alloca la memoria
	
	move $s1, $v0			# 			$s1 <- vett
	
	li $s2, 0				# int x= 0 	$s2 <- x
	addi $s3, $s0, -1		# 			$s3 <- n-1
while:	
	bgt $s2, $s3, exit
	add $t0, $s1, $s2		# indirizza il vettore
	lb  $t1, ($t0)		#			$t1 <- vett[x]
	bne $t1, $s3, else		#  if(vett[x]==N-1){
	sb  $zero, ($t0)		#	vett[x]=0;
	addi $s2, 1
	b while
else:
	addi $t1, 1
	sb $t1, ($t0)				# vett[x]++
	
	li $t0, 1					# 			$t0 <- i
for_out:
	bge $t0, $s0, end_for_out	#   while(i<N){
	li $t1, 0					#			$t1 <- j
for_in:
	bge $t1, $t0, end_for_in	#    while(j<i){
	add $t2, $s1, $t0
	lb  $t2, ($t2)			#		$t2 <- vett[i]
	add $t3, $s1, $t1
	lb  $t3, ($t3)			#		$t3 <- vett[j]
	beq $t2, $t3, end_else		#	if((vett[i]==vett[j]) ||	
	sub $t4, $t0, $t1			#		$t4 <- (j-i)
	sub $t5, $t2, $t4			#		$t5 <- vett[i]-(i-j)
	beq $t5, $t3, end_else		#	(vett[i]-(i-j)==vett[j]) ||
	add $t5, $t2, $t4			#		$t5 <- vett[i]+(i-j)
	beq $t5, $t3, end_else		#	(vett[i]+(i-j)==vett[j])) goto while;
	
	addi $t1, 1				# j++
	b for_in
	
end_for_in:
	addi $t0, 1				# i++
	b for_out
	
end_for_out:

	li $t0, 0					# 			$t0 <- i
for_out2:
	bge $t0, $s0, end_for_out2	#   while(i<N){
	li $t1, 0					#			$t1 <- j
for_in2:
	bge $t1, $s0, end_for_in2	#    while(j<N){
	add $t2, $s1, $t0
	lb  $t2, ($t2)			#			$t2 <- vett[i]
	bne $t2, $t1, else2		# 		if(vett[i] == j){
	
	li $v0, 4
	la $a0, queen_cell			# stampa regina
	syscall

	addi $t1, 1				# j++
	b for_in2
	
else2:
	li $v0, 4					# }else{ 
	la $a0, empty_cell			# stampa cella vuota }
	syscall
	
	addi $t1, 1				# j++
	b for_in2
	
end_for_in2:
	li $v0, 4
	la $a0, rtrn				# a capo
	syscall

	addi $t0, 1				# i++
	b for_out2
	
end_for_out2:
	li $v0, 4
	la $a0, rtrn				# a capo
	syscall

end_else:
	li $s2, 0
	b while

exit:
	li $v0, 10				# esce dal programma
	syscall

#class NQ{#  //Combinazioni#  public static void nextOne(int[] vett, int x, int N){#   while(x<=N){#     if(vett[x]>=N+1){#        vett[x]=0;#        x++;#     }else{#       vett[x]++;#   	test(vett,N);# 	x=0;#  }#}}#  //soluzione veritiera?# public static void test(int[] vett, int N){#   for(int j=1; j<N+1; j++)#     for(int k=0; k<j; k++)#       if((vett[j]==vett[k]) || (vett[j]-(j-k)==vett[k]) || (vett[j]+(j-k)==vett[k]))#         return;#   printV(vett,N);# }# //Visualizzazione grafica# public static void printV(int[] vett,int N){#  for(int i=0; i<N+1; i++){#    for(int j=0; j<N+1; j++)#      if(vett[i] == j) System.out.print("[M]");#      else System.out.print("[ ]");#    System.out.println();#  }#  System.out.println();# }# public static void main(String[] args){#   int N=4; //N a scelta#   int[] vett= new int[N];#   nextOne(vett,0,N-1);# }}
	
	