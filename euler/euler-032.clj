; Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

(defn pandigital? [x y]
 (= 9 (count (set (seq (str (* x y) x y))))))

(defn pandigital? [t]
 (let [x (first t) y (second t) mmp (seq (str (* x y) x y))]
  (and (= 9 (count mmp))
   (= 9 (count (filter #(not (= \0 %)) (set mmp)))))))
       
(filter pandigital? (for [x (range 2 5000) y (range 2 5000) :while (> x y)] [x y]))
; ([138 42] [157 28] [159 48] [186 39] [198 27] [297 18] [483 12] [1738 4] [1963 4])

(reduce + (distinct (map #(* (first %) (second %)) (filter pandigital? (for [x (range 2 5000) y (range 2 5000) :while (> x y)] [x y])))))
