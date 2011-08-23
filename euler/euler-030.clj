; Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.

(defn fifth [n]
 (* n n n n n))

(defn sum-of-fifths? [n]
 (= n (reduce + (map #(fifth (char-to-digit %)) (seq (str n))))))

; (4150 4151 54748 92727 93084 194979)
(reduce + (filter sum-of-fifths? (range 10 200000)))
