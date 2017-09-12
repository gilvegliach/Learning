
;; Codifica di Huffman per la compressione di documenti
;; (1/03/07)


(read-case-sensitive #t)


;; Dato astratto "Struttura di Huffman"
;;
;; Operazioni:
;;
;;   (huffman-structure <sym> <wgt>)  :  symbol x weight -> huffman_str
;;
;;   (huffman-merge <lft> <rgt>)      :  huffman_str x huffman_str -> huffman_str
;;
;;   (huffman-weight <str>)           :  huffman_str -> weight
;;
;;   (huffman-code <sym> <str>)       :  symbol x huffman_str -> code
;;
;;   (huffman-decode <cod> <str>)     :  code x huffman_str -> symbol x code


;; Realizzazione della struttura di Huffman


;; Struttura iniziale: simbolo e peso

(define huffman-structure  ; valore: struttura di huffman
  (lambda (sym wgt)        ; sym: simbolo, wgt: peso
    (list
     sym                   ; albero di huffman
     wgt                   ; peso complessivo
     (list sym)            ; lista ordinata dei simboli
     (list "")             ; lista corrispondente dei codici
     )
    ))


;; Aggregazione di (sotto)strutture

(define huffman-merge      ; valore: struttura di huffman
  (lambda (lft rgt)        ; lft, rgt: strutture di huffman
    (list
     (cons (car lft) (car rgt))        ; costruzione albero
     (+ (cadr lft) (cadr rgt))         ; somma dei pesi
     (append (caddr lft) (caddr rgt))  ; append liste siboli
     (append                           ; append liste codici
      (map (lambda (s) (string-append "0" s)) (cadddr lft))
      (map (lambda (s) (string-append "1" s)) (cadddr rgt))
     ))                    ; ai codici preesistenti viene
    ))                     ; aggiunto il prefisso "0" o "1"


;; Acquisizione di informazione sul peso

(define huffman-weight cadr)


;; Codifica binaria di un simbolo, data una struttura di Huffman

(define huffman-code       ; valore: stringa di cifre binarie
  (lambda (sym str)        ; sym: simbolo, str: struttura di Huffman
    (get-code sym (caddr str) (cadddr str))
    ))


;; Decodifica del primo simbolo e rimozione dei corrispondenti bit

(define huffman-decode     ; valore: coppia <simbolo, stringa binaria>
  (lambda (cod str)        ; cod: stringa binaria, str: struttura di Huffman
    (pick cod (car str))
    ))


;; Procedure di supporto

;; Ricerca del codice del simbolo sym,
;; date le liste dei simboli e delle corrispondenti codifiche;
;; si assume che sym appartenga alla lista dei simboli

(define get-code             ; valore: stringa di cifre binarie
  (lambda (sym symbs codes)  ; sym: simbolo; symbs, codes: liste
    (if (equal? sym (car symbs))
        (car codes)
        (get-code sym (cdr symbs) (cdr codes))
        )
    ))

;; Individuazione del primo simbolo di una codifica cod,
;; dato l'albero di Huffman che rappresenta le codifiche dei simboli:
;; viene restituita la coppia <simbolo, resto della codifica>;
;; si assume che cod sia corretto

(define pick                 ; valore: coppia <simbolo, stringa>
  (lambda (cod tree)         ; cod: stringa, tree: albero di Huffman
    (cond ((symbol? tree)
           (cons tree cod))
          ((char=? (string-ref cod 0) #\0)
           (pick (substring cod 1) (car tree)))
          (else
           (pick (substring cod 1) (cdr tree)))
          )
    ))


;; Dato astratto "Sequenza ordinata di strutture di Huffman"
;;
;; Operazioni:
;;
;;   (sorted-list)             :  _ -> sorted_list
;;
;;   (sorted-add <str> <lst>)  :  huffman_str x sorted_list -> sorted_list


;; Realizzazione della sequenza ordinata


;; Lista vuota (procedura di zero argomenti)

(define sorted-list  ; valore: sequenza ordinata
  (lambda ()
    null
    ))


;; Inserimento di un nuovo elemento

(define sorted-add   ; valore: sequenza ordinata (per peso)
  (lambda (str lst)  ; str: struttura di Huffman, lst: sequenza ordinata
    (cond ((null? lst)
           (list str))
          ((< (huffman-weight str) (huffman-weight (car lst)))
           (cons str lst))
          (else
           (cons (car lst) (sorted-add str (cdr lst))))
          )
    ))


;; Applicazione degli strumenti introdotti


;; Costruzione di una struttura di Huffman
;; data una sequenza ordinata di styrutture

(define build-structure    ; valore: struttura di Huffman
  (lambda (lst)            ; lst: sequenza ordinata
    (if (null? (cdr lst))
        (car lst)
        (build-structure (sorted-add (huffman-merge (car lst) (cadr lst)) (cddr lst)))
        )
    ))


;; Compressione di una sequenza di siboli:
;; confronta con una codifica binaria dei simboli a parola fissa

(define compress           ; valore: stringa di cifre binarie
  (lambda (seq str)        ; seq: sequenza di simboli, str: struttura di Huffman
    (if (null? seq)
        ""
        (string-append (huffman-code (car seq) str) (compress (cdr seq) str))
        )
    ))


(define decompress         ; valore: sequenza di simboli
  (lambda (cod str)        ; cod: codifica binaria, str: struttura di Huffman
    (if (= (string-length cod) 0)
        null
        (let ((dec (huffman-decode cod str))
              )
          (cons (car dec) (decompress (cdr dec) str))
        ))
    ))


;; Esempio:
;;
;; (define q0 (sorted-list))
;; (define q1 (sorted-add (huffman-structure 'A 2) q0))
;; (define q2 (sorted-add (huffman-structure 'T 6) q1))
;; (define q3 (sorted-add (huffman-structure 'C 3) q2))
;; (define q4 (sorted-add (huffman-structure 'G 1) q3))
;;
;; (define huffman-str (build-structure q4))
;;
;;
;; (compress '(A T T C T A C C T T G T) huffman-str)
;;
;; (decompress "111001001111010001100" huffman-str)
