(defn collatz
 [n]
 (if (even? n) (/ n 2) (+ (* 3 n) 1)))

(defn chain
 [n]
 (cond
  (= n 1) '(1)
  :else (conj (chain (collatz n)) n)))

;This is slow (11002.915477 msecs), though it solves the problem.
(first (reduce (fn [x y](if (> (count x) (count y)) x y)) (map chain (range 3 1000000 2))))

; I tried to memoize the two support functions, but it didn't help.
