(ns binary)

(defn to-decimal [s]
  (first
    (reduce
      (fn [[sum base] e]
        [(if (= \1 e)
           (+ sum base)
           sum)
         (* base 2)])
      [0 1]
      (reverse s))))