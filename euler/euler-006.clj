;The sum of the squares of the first ten natural numbers is,
; 12 + 22 + ... + 102 = 385
;The square of the sum of the first ten natural numbers is,
; (1 + 2 + ... + 10)2 = 552 = 3025
;Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025  385 = 2640.
;Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

(reduce + (map #(* x x) (range 1 11)))

(let [x (reduce + (range 1 11))] (* x x))

(defn euler-6
 [y]
 (let [spread (range 1 (inc y))]
  (- (let [x (reduce + spread)] (* x x))
     (reduce + (map #(* % %) spread)))))
