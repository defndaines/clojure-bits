(ns collatz-conjecture
  "The Collatz Conjecture or 3x+1 problem can be summarized as follows:

  Take any positive integer n. If n is even, divide n by 2 to get n / 2. If n
  is odd, multiply n by 3 and add 1 to get 3n + 1. Repeat the process
  indefinitely. The conjecture states that no matter which number you start
  with, you will always reach 1 eventually.

  Given a number n, return the number of steps required to reach 1.")

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
