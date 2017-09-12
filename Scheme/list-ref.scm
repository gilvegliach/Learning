(define list-ref
  (lambda (l n)
    (if (= n 1)
        (car l)
        (list-ref (cdr l) (- n 1)))))

(define list-rev
  (lambda (l)
    (if (null? l)
        ()
        (append (list-rev (cdr l)) (list (car l))))))