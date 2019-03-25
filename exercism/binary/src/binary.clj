(ns binary
  "Convert a binary number, represented as a string (e.g., '101010'), to its
  decimal equivalent using first principles.")

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
