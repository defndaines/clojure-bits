(ns nth-prime
  "Given a number n, determine what the nth prime is.

  By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
  that the 6th prime is 13.")

(defn- prime? [x known]
  (every? #(ratio? (/ x %)) known))

(defn nth-prime [n]
  {:pre [(> n 0)]}
  (loop [prime [2]
         odds (iterate (partial + 2) 3)]
    (if (= n (count prime))
      (last prime)
      (let [check (first odds)]
        (if (prime? check prime)
          (recur (conj prime check) (rest odds))
          (recur prime (rest odds)))))))
