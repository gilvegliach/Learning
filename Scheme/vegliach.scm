; To: demaria [at] dimi.uniud.it
; Surname:                Vegliach
; Name:                   Gil
; Immatriculation number: 80568

(define po2                                            ; value-> integer
  (lambda (n)                                          ; n: integer
    (if  (= (remainder n 2) 1)
         0                                             ; base case
         (+ 1 (po2 (quotient n 2))))))                 ; recursive case
; OUTPUT:
; > (po2 15)
; 0
; > (po2 12)
; 2
; > (po2 40)
; 3

(define po2-tr                                        ; value-> : integer
  (lambda (n)                                         ; n: integer
    
    (define po2-tr-aux                                ; value-> integer, inner scope - not visible globally
      (lambda (n i)                                   ; n,i: integer (i is a placeholder for the result)
        (if (= (remainder n 2) 1)
            i                                         ; base case
            (po2-tr-aux (quotient n 2) (+ i 1)))))    ; recursive case
    (po2-tr-aux n 0)))                                ; call the procedure on i=0 (n is in the n in the outer-scope)

; OUTPUT:
; > (po2-tr 12)
; 2
; > (po2-tr 3)
; 0
; > (po2-tr 40)
; 3
; > (po2-tr 15)
; 0


(define subst                                         ; value-> : string
  (lambda (s out in)                                  ; out: string (1 char), in: string (1 char)
    (cond ((not (string? out))                        ; code for error handling
           "Error (0): out is not a string") 
          ((not (string? in))
           "Error (1): in is not a string")
          ((not (= (string-length out) 1)) 
           "Error (2): out is not a string of length 1")
          ((not (= (string-length in) 1)) 
           "Error (3): in is not a string of length 1")
          (else                                      ; main procedure's code
           
           
           (if (= (string-length s) 0)
               ""                                    ; base case
               (let ((first-char (substring s 0 1)))
                 (string-append                      ; recursive case
                  (if (string=? first-char out) 
                      in
                      first-char)
                  (subst (substring s 1) out in))))))))

; OUTPUT:
; > (subst "leggo" "ga" "s")
; "Error (2): out is not a string of length 1"
; > (subst "leggo" "g" "sa")
; "Error (3): in is not a string of length 1"
; > (subst "leggo" 1 "s")
; "Error (0): out is not a string"
; > (subst "leggo" "g" 1)
; "Error (1): in is not a string"
; > (subst "leggo" "g" "s")
; "lesso"

(define subst-tr                                      ; value-> : string
  (lambda (s out in)                                  ; out: string (1 char), in: string (1 char)
    (define subst-tr-aux                              ; parameter and return-value same as above;
      (lambda (s out in result)                       ; result: string - placeholder for the result
    
    (cond ((not (string? out))                        ; code for error handling
           "Error (0): out is not a string") 
          ((not (string? in))
           "Error (1): in is not a string")
          ((not (= (string-length out) 1)) 
           "Error (2): out is not a string of length 1")
          ((not (= (string-length in) 1)) 
           "Error (3): in is not a string of length 1")
          (else                                      ; main procedure's code
           
           
           (if (= (string-length s) 0)
               result                                ; base case
               (let ((first-char (substring s 0 1)))
                   (subst-tr-aux (substring s 1)     ; recursive case
                                out 
                                in
                                (string-append
                                   result
                                   (if (string=? first-char out)  ; fourth parameter is taken from in
                                     in                           ; or from the string, depending wheter
                                     first-char))                 ; we want to substituite it or not
                    )))))))
    (subst-tr-aux s out in "")))
                                   
; OUTPUT:        
; > (subst-tr "lesso" "s" "g")
; "leggo"
; > (subst-tr "let" "asd" "g")
; "Error (2): out is not a string of length 1"


(define composition                   ; value-> : #<procedure>
  (lambda (f g)                       ; f, g:  #<procedure> (1 real argument)
    (lambda (x) (f (g x)))))          ; returns f°g

(define f1
  (lambda (x)
    ((composition (lambda (y) (* y y)) 
                  cos) x)))
; OUTPUT
; > (f1 0)
; 1

(define f2
  (lambda (x)
    ((composition sqrt sin) x)))

; OUTPUT
; > (f2 3.14)
; 0.03990805578435046
;
; (3.14 tends to PI (where PI is 3.14159265...), so
; sinx tends to 0 anche sqrt(sinx) tends to 0 too