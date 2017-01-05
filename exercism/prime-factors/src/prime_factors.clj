(ns prime-factors)

(defn of [n]
  (loop [x n f 2 acc []]
    (if (= 1 x)
      acc
      (if (ratio? (/ x f))
        (recur x (inc f) acc)
        (recur (/ x f) f (conj acc f))))))