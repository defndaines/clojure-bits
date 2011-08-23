;A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91  99.
;Find the largest palindrome made from the product of two 3-digit numbers.

(defn palindrome?
 [x]
 (let [rev (seq (str x))]
  (= rev (reverse rev))))

(defn next-palindrome-down
 [x]
 (let [y (dec x)]
 (cond
  (palindrome? y) y
  :else (next-palindrome-down y))))

(defn three-digit?
 [x]
 (and (integer? x) (< x 1000) (> x 100)))

(defn ceil
 [x]
 (int (. java.lang.Math (ceil x))))

(defn sqrt
 [x]
 (. java.lang.Math (sqrt x)))

(defn valid-product
 [goal]
 (filter (fn [x] (integer? (/ goal x))) (range (ceil (sqrt goal)) 1000)))
 
(defn euler-4
 [x]
 (cond
  (and (palindrome? x) 
       (not (= '() (filter three-digit? (valid-product x)))))
    x
  :else (euler-4 (next-palindrome-down x))))

;Answer = 906609
