
;; Problema delle "n regine"
;;
;; Ultimo aggiornamento: 8/04/10


;; Dato astratto configurazione della "scacchiera"
;;
;; Operazioni:
;;
;;   (empty-board n)        :  interi -> scacchiere
;;
;;   (size B)               :  scacchiere -> interi
;;
;;   (queens-on B)          :  scacchiere -> interi
;;
;;   (under-attack? B i j)  :  scacchiere x indici x indici -> booleani
;;
;;   (add-queen B i j)      :  scacchiere x indici x indici -> scacchiere
;;
;;   (arrangement B)        :  scacchiere -> descrizioni
;;
;;
;; (empty-board n) :        Costruttore della scacchiera n x n vuota;
;; (size B) :               Dimensione n della scacchiera B;
;; (queens-on B) :          Numero di regine collocate nella scacchiera B;
;; (under-attack? B i j) :  La posizione i j e' sotto scacco?
;; (add-queen B j) :        Scacchiera che si ottiene dalla configurazione B
;;                          aggiungendo una nuova regina in posizione i j
;;                          (si assume che la posizione non sia sotto scacco);
;; (arrangement B) :        Descrizione "esterna" della configurazione
;;                          (esempi: lista di posizioni, stringa, immagine).


;; Realizzazione alternativa del dato astratto "Scacchiera"


(define empty-board             ; scacchiera vuota size x size
  (lambda (n)
    (list
     n                          ; 0) dimensione scacchiera
     null                       ; 1) righe sotto scacco
     null                       ; 2) colonne sotto scacco
     null                       ; 3) diagonali \ sotto scacco
     null                       ; 4) diagonali / sotto scacco
     ""                         ; 5) disposizione: stringa di coordinate
     )
    ))


(define size car)               ; dimensione scacchiera


(define queens-on               ; numero regine collocate sulla scacchiera
  (lambda (board)
    (length (list-ref board 1))
    ))


(define under-attack?           ; la posizione <i,j> e' sotto scacco?
  (lambda (board i j)
    (or (belongs? i       (list-ref board 1))
        (belongs? j       (list-ref board 2))
        (belongs? (- i j) (list-ref board 3))
        (belongs? (+ i j) (list-ref board 4))
        )
    ))


(define add-queen               ; valore: scacchiera con una nuova regina
  (lambda (board i j)           ;         in posizione <i,j>
    (list
     (car board)
     (cons i        (list-ref board 1))
     (cons j        (list-ref board 2))
     (cons (- i j)  (list-ref board 3))
     (cons (+ i j)  (list-ref board 4))
     (string-append
         (code i j) (list-ref board 5))
     )
    ))


(define arrangement             ; lista indici di colonna delle regine
  (lambda (board)
    (list-ref board 5)
    ))


;; Appartenenza di un elemento a un insieme

(define belongs?
  (lambda (x set)
    (cond ((null? set) false)
          ((= x (car set)) true)
          (else (belongs? x (cdr set)))
          )))


;; Codifica secondo le convenzioni scacchistiche (fino a 15 x 15)

(define code
  (lambda (i j)
    (string
     #\space
     (string-ref cols j)
     (string-ref rows i)
     #\space
     )))

(define rows " 123456789ABCDEF")
(define cols " abcdefghijklmno")

