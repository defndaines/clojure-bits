(ns prime-factors
  "Compute the prime factors of a given natural number.

  A prime number is only evenly divisible by itself and 1.

  Note that 1 is not a prime number.")

(defn of [n]
  (loop [x n f 2 acc []]
    (if (= 1 x)
      acc
      (if (ratio? (/ x f))
        (recur x (inc f) acc)
        (recur (/ x f) f (conj acc f))))))
