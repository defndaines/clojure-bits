(defn even-fibs
 [x y limit]
 (cond (> (+ x y) limit) '()
  (even? (+ x y)) (cons (+ x y) (even-fibs y (+ x y) limit))
  :else (even-fibs y (+ x y) limit)))

(reduce + (even-fibs 0 1 4000000))

; 4613732
