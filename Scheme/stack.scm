(define empty-stack null)
(define empty? null)
(define top car)
(define push cons)
(define pop cdr)
(define elab-op
  (lambda (op stack)
    (push (op (top (pop stack)) (top stack) ) (pop (pop stack)))))

(define polish
  (lambda (l stack)
    (if (null? l) 
        (top stack)
        (if (number? (car l)) 
            (polish (cdr l) (push (car l) stack))
            (polish (cdr l)
                    (elab-op (eval (car l)) stack))) 
            )))

; (polish '( 5 4 + 3 * 2 /) empty-stack) -> 13.5
; (5+4)+3/2 = 13.5

