(ns collatz-conjecture)

(defn- step [n]
  (if (even? n)
    (/ n 2)
    (+ (* 3 n) 1)))

(defn collatz [n]
  (when (< n 1)
    (throw (IllegalArgumentException. "Only positive integers.")))
  (loop [steps 0
         x n]
    (if (= 1 x)
      steps
      (recur (inc steps) (step x)))))
