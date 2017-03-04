(ns nth-prime)

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
