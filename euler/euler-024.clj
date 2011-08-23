;What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

; Starts with 2 -> [2 0 1 3 4 5 6 7 8 9] is 725761st

; [2 6 7

(defn fac
 [x]
 (if (zero? x) 1 (* x (fac (dec x)))))

; Total permutations = (fac 10)
; Each digit is (quot (fac x) (fac (dec x))) in the remaining list
; [0 1 2 3 4 5 6 7 8 9]

(defn rember
 "Return a new list of atoms, removing the first occurrence of a from lat"
 [a lat]
 (cond
  (null? lat) '()
  :else (cond
   (= (first lat) a) (rest lat)
   :else (cons (first lat) (rember a (rest lat))))))

(defn swap
 "Return a new pair, removing the first occurrence of a from the first element of pair and placing it in the second element"
 [a pair]
 (let [x (nth (first pair) a)]
  (cons (rember x (first pair)) (cons x (rest pair)))))

(defstruct data :lst :n)
(def init (struct data '(0 1 2 3 4 5 6 7 8 9) 0))

; Flawed. If I want 5th, I have to enter 4
(defn lexical-nth
 [n dat]
 (if (empty? (:lst dat)) (:n dat)
  (let [next-perm (fac (dec (count (:lst dat))))
        pos (quot n next-perm)
        x (nth (:lst dat) pos)]
   (lexical-nth
    (rem n next-perm)
    (struct data (rember x (:lst dat)) (+ x (* 10 (:n dat))))))))
