;What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?

(defn corner-sequence
 ([side] (corner-sequence side '(1) 2))
 ([side corners i]
  (cond
   (>= i side) corners
   (= (count corners) (inc (* i 2))) (corner-sequence side corners (+ 2 i))
   :else (recur side (conj corners (+ i (first corners))) i))))

(reduce + (corner-sequence 1001))
