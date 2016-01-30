(define list-head 
  (lambda (l k)
    (if (= k 0)
        null
        (cons (car l) (list-head (cdr l) (- k 1) )))))

(define sum
  (lambda (l s)
    (define sum-aux
      (lambda (l s carry) 
        (if (null? l)
           (if (not (= carry 0))  
               (list carry)
               null)
        (let* ((last-digit-l (car (list-tail l (- (length l) 1))))
               (last-digit-s (car (list-tail s (- (length s) 1))))  
               (sum-digits   (+ last-digit-s last-digit-l carry))
               (next-carry   (quotient sum-digits 10))
               (last-digit   (- sum-digits (* next-carry 10))))
          (append (sum-aux (list-head l (- (length l) 1))
                           (list-head s (- (length s) 1))
                           next-carry)
                  (list last-digit))))))
    
    (define fill-zero
      (lambda (l no-of-zeros)
        (if (< no-of-zeros 1) 
            l
            (cons 0 (fill-zero l (- no-of-zeros 1))))))
    
    (let* ((len-s (length s))
          (len-l (length l)))
        
    (sum-aux (fill-zero l (- len-s len-l)) 
             (fill-zero s (- len-l len-s)) 
             0))))
               

(define bst
  (lambda (n m)
    (if (= n m)
        (cons n '())
        (if (> n m) 
        (cons '() '())
        (let ((mid (quotient (+ m n) 2)))
        (cons mid (cons(bst n (- mid 1))
                  (bst (+ mid 1) m))))))))

;> (bst 1 9)
;(5 (2 (1) 3 (()) 4) 7 (6) 8 (()) 9)

(define sum-2
  (lambda (l s)
    (define sum-2-aux
      (lambda (l s carry)
        (cond ((and (null? l) (null? s)) 
                (if (> carry 0) `(,carry) '()))
              ((null? l) (sum-2-aux '(0) s carry))
              ((null? s) (sum-2-aux l '(0) carry))
              (else (cons (remainder (+ (car l) (car s) carry) 10)
                          (sum-2-aux (cdr l) 
                                     (cdr s)
                                     (quotient (+ (car l) (car s) carry) 10))
                     )))))
    (reverse (sum-2-aux (reverse l)
               (reverse s)
               0))
    ))