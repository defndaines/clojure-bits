(ns trinary)

(def trinity {\0 0
              \1 1
              \2 2})

(defn to-decimal [s]
  (if (every? #{\0 \1 \2} s)
    (first
      (reduce
        (fn [[sum base] e]
          [(+ sum (* base (get trinity e)))
           (* base 3)])
        [0 1]
        (reverse s)))
    0))