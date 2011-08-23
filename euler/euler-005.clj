;2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
;What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

; Notice that 2520 = (* 5 7 8 9) = (* 2 2 2 3 3 5 7) --> The prime factors
; The primes from 1 - 10 are (2 3 5 7)

; The primes from 1 - 20 are (2 3 5 7 11 13 17 19)
; Suspected answer (* 2 2 2 2 3 3 5 7 11 13 17 19)

; Used to check, but not necessary in calculation
(defn divisible?
 [x]
 (= 10 (count (filter (fn [y] (integer? (/ x y))) (range 11 21)))))

; This version leads to stack overflow!!!
(defn euler-5
 [x]
 (cond
  (divisible? x) x
  :else (euler-5 (inc x))))

; This takes too long, although it does work
(defn euler-5
 [seed]
 (loop [x seed]
  (if (divisible? x) x
   (recur (inc x)))))

(defn pow 
 [x y]
  (. java.lang.Math (pow x y)))

(defn largest-power
 ([x limit]
  (if (> x limit) x (largest-power x 2 limit)))
 ([x y limit]
  (if (> (pow x y) limit) (int (pow x (dec y))) (largest-power x (inc y) limit))))
 
; This answers it for any number
(defn euler-5
 [x]
 (reduce * (map (fn [y] (largest-power y x)) (filter prime? (range 2 (inc x))))))

