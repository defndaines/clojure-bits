(ns perfect-numbers)

(defn- factors [n]
  (filter #(zero? (rem n %)) (range 1 n)))

(defn classify [n]
  (let [sum (reduce + (factors n))]
    (cond
      (zero? sum) (throw (IllegalArgumentException.))
      (> sum n) :abundant
      (= sum n) :perfect
      (< sum n) :deficient)))
