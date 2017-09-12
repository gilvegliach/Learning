;; (get-moves <board>) -> list            mosse disponibili
;; (get-state <board>) -> integer              'no
;;                                        'max
;;                                        'min
;;                                        'draw
;;
;; (do-move <board> <position> <player>) -> board 
;;                                        fa la mossa  
;; (max-move <board>) -> number           miglior mossa per max
;; (min-move <board>) -> number           miglior mossa per min

;; move =>  (posizione . valore)

(define do-move 
  (lambda (board pos player)
    (if (= pos 1)  ; conta da 1
        (cons (if (equal? player 'max) 'x 'o) 
              (cdr board))
        (cons (car board) (do-move board (- pos 1) player)))))

(define max-move
  (lambda (board)
    (let ((state (get-state board)))
      (cond ((equal? 'max) 1)
            ((equal? 'min) -1)
            ((equal? 'draw) 0)
            (else
              (max 
                (map (lambda (move)            ; trova la miglior mossa
                       (min-move (do-move board (car move) 'max)))
                       (get-moves board)))
              )))))

(define min-move
  (lambda (board)
    (let ((state (get-state board)))
      (cond ((equal? 'max) 1)
            ((equal? 'min) -1)
            ((equal? 'draw) 0)
            (else
              (min 
                (map (lambda (move)            ; trova la miglior mossa
                       (max-move (do-move board (car move) 'min)))
                       (get-moves board))))))))