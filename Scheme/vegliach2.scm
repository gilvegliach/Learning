;*********************************************
;  Veglich Gil
;  Matricola: 80568
;  Relazione di laboratorio (2)
;*********************************************
;
; Somma di liste, list+ (sum)
; si veda in fondo per la prima versione "sum"

(define list+             ; valore: lista
  (lambda (l s)           ; l,s: liste
    (define list+_aux     ; procedura di supporto
      (lambda (l s carry) ; l,s: liste; carry: integer
        (cond ((and (null? l) (null? s)) 
               (if (> carry 0) `(,carry) '())) 
              ((null? l) 
               (list+_aux '(0) s carry)) ; |s| > |l|
              ((null? s) 
               (list+_aux l '(0) carry)) ; |l| > |s|
              (else (cons (remainder (+ (car l) (car s) carry) 
                                     10) ; somma modulo 10
                          (list+_aux (cdr l) 
                                     (cdr s)
                                     (quotient (+ (car l) (car s) carry) 
                                               10)) ; riporto
                          )))))
    (reverse (list+_aux (reverse l)
                        (reverse s)
                        0))     ; il primo riporto è zero
    ))

; Procedura di supporto non strettamente necessaria

(define rev       ; analogo di reverse
  (lambda (l)     ; inverte la lista l
    (define rev-aux ; ricorsione di coda
      (lambda (l s)
        (if (null? l)
            s
            (rev-aux (cdr l)
                     (cons (car l)
                           s)))))
    (rev-aux l null)))


; OUTPUT
; > (list+ '(3 5 9 8) '(1 9 7 2))
; (5 5 7 0)


; Binary Search Tree

(define bst              ; valore: albero (lista)
  (lambda (n m)          ; [n,m]: intervallo di numeri
    (if (= n m)          ; casi base: 1) un solo elemento
        (cons n (cons '()'()))   
        (if (or (> n m) (< m n)) ; 2) albero vuoto
            (cons '() '())
            (let ((mid (quotient (+ m n 1) 2))) ; +1
              (cons mid (cons (bst n (- mid 1))
                              (bst (+ mid 1) m))))))))

; > (bst 1 9)
; (5 (3 (2 (1 ()) ()) 4 ()) 8 (7 (6 ()) ()) 9 ())

; Aggiungere il Teachpach graphics.ss

(open-graphics) 
(define max-x 640)
(define max-y 480)
(define window (open-viewport "window" max-x max-y))

(define draw-tree-aux                               ; procedura di supporto di plotting
  (lambda (tree old-pos curr-pos x-offset y-offset) ; new-offset server per ridurre 
    (let* ((new-x-offset   (/ x-offset 2))          ; l'ampiezza dell'albero
           (pos-l (make-posn (- (posn-x curr-pos)    ; calcolo dei prossimi offset 
                                new-x-offset)
                             (+ (posn-y curr-pos)
                                y-offset)))
           (pos-r  (make-posn (+ (posn-x curr-pos) 
                                 new-x-offset)
                              (+ (posn-y curr-pos)
                                 y-offset)))
           (radius             30)                   ; raggio del cerchio che contiene
           (pos-circle (make-posn                    ; i numeri
                        (- (posn-x curr-pos) 
                           (/ radius 2))
                        (- (posn-y curr-pos) 
                           (/ radius 2)))))
      
      (if (or (null? tree) (null? (car tree)))       ; se è vuoto
          ((clear-line window) old-pos curr-pos)     ; cancella l'ultima riga
          (begin                                     ; altrimenti disegna...
            ((draw-line window) curr-pos pos-l)
            ((draw-line window) curr-pos pos-r)       ;(1 1 1) = bianco
            ((draw-solid-ellipse window) pos-circle radius radius (make-rgb 1 1 1) )
            ((draw-ellipse window) pos-circle radius radius)            ; contorno
            ((draw-string window) curr-pos (number->string (car tree))) ; numero 
            (draw-tree-aux (cadr tree) curr-pos pos-l new-x-offset y-offset) 
            (draw-tree-aux (cddr tree) curr-pos pos-r new-x-offset y-offset))))))

(define draw-tree   ; procedura principale di disegno
  (lambda (tree)
    ((clear-viewport window))  ; cancella lo schermo
    (draw-tree-aux tree (make-posn 320 15) 
                   (make-posn 320 15) 
                   100 
                   100)))

(draw-tree (bst 1 9))

; Genera un disegno in una finestra


;(define list-head 
;  (lambda (l k)
;    (if (= k 0)
;        null
;        (cons (car l) (list-head (cdr l) (- k 1) )))))
;
;(define sum
;  (lambda (l s)
;    (define sum-aux
;      (lambda (l s carry) 
;        (if (null? l)
;           (if (not (= carry 0))  
;               (list carry)
;               null)
;        (let* ((last-digit-l (car (list-tail l (- (length l) 1))))
;               (last-digit-s (car (list-tail s (- (length s) 1))))  
;               (sum-digits   (+ last-digit-s last-digit-l carry))
;               (next-carry   (quotient sum-digits 10))
;               (last-digit   (- sum-digits (* next-carry 10))))
;          (append (sum-aux (list-head l (- (length l) 1))
;                           (list-head s (- (length s) 1))
;                           next-carry)
;                  (list last-digit))))))
;    
;    (define fill-zero
;      (lambda (l no-of-zeros)
;        (if (< no-of-zeros 1) 
;            l
;            (cons 0 (fill-zero l (- no-of-zeros 1))))))
;    
;    (let* ((len-s (length s))
;          (len-l (length l)))
;        
;    (sum-aux (fill-zero l (- len-s len-l)) 
;             (fill-zero s (- len-l len-s)) 
;             0))))
;               
;
;(define bst
;  (lambda (n m)
;    (if (= n m)
;        (cons n (cons '()'()))
;        (if (or (> n m) (< m n)) 
;        (cons '() '())
;        (let ((mid (quotient (+ m n 1) 2)))
;        (cons mid (cons (bst n (- mid 1))
;                  (bst (+ mid 1) m))))))))
;
