(ns trinary
  "Convert a trinary number, represented as a string (e.g. '102012'), to its
  decimal equivalent using first principles.")

(def trinity {\0 0
              \1 1
              \2 2})

(defn to-decimal
  "Given a string representation of a trinary number, convert it to decimal
  (as an integer)."
  [s]
  (if (every? #{\0 \1 \2} s)
    (first
      (reduce
        (fn [[sum base] e]
          [(+ sum (* base (get trinity e)))
           (* base 3)])
        [0 1]
        (reverse s)))
    0))
