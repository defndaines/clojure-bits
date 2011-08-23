;Name scores
(defn char-val
 [ch]
 (- (int ch) 64))

(defn worth
 [name]
 (reduce (fn [a b] (+ a (char-val b))) 0 (seq name)))

(defn name-score
 [pos name]
 (* pos (worth name)))

(defn euler-022
 ([lst] (euler-022 lst 1 0))
 ([lst i acc]
  (if (empty? lst) acc
   (recur (rest lst) (inc i) (+ acc (name-score i (first lst)))))))

(euler-022 (sort (split (replace (slurp "names.txt") "\"" "") #",")))
