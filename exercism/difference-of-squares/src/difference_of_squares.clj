(ns difference-of-squares
  "Find the difference between the square of the sum and the sum of the
  squares of the first N natural numbers.")

(defn square-of-sums [n]
  (let [sum (reduce + (range 1 (inc n)))]
    (* sum sum)))

(defn sum-of-squares [n]
  (reduce (fn [acc e] (+ acc (* e e))) (range 1 (inc n))))

(defn difference [n]
  (- (square-of-sums n) (sum-of-squares n)))
