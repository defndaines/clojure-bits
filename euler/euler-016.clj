; 2^(15) = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
; What is the sum of the digits of the number 2^(1000)?

(defn pow
 [x y]
 (reduce (fn [a b] (* a x)) x (range 1 y)))

(defn char-to-digit
 [ch]
 (- (int ch) (int \0)))

(reduce + (map char-to-digit (seq (str (pow 2 1000)))))  
