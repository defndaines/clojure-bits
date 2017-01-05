(ns sieve)

(defn sieve [n]
  (loop [prime []
         remain (range 2 (inc n))]
    (if-let [check (first remain)]
      (recur (conj prime check) (filter #(ratio? (/ % check)) (rest remain)))
      prime)))