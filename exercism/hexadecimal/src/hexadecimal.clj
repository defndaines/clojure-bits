(ns hexadecimal
  "Convert a hexadecimal number, represented as a string (e.g., '0af8c'), to
  its decimal equivalent using first principles.")

(def hexes {\0 0  \1 1  \2 2  \3 3
            \4 4  \5 5  \6 6  \7 7
            \8 8  \9 9  \a 10 \b 11
            \c 12 \d 13 \e 14 \f 15})

(defn hex-to-int [s]
  (if (every? (set (keys hexes)) s)
    (first
      (reduce
        (fn [[sum base] e]
          [(+ sum (* base (get hexes e)))
           (* base 16)])
        [0 1]
        (reverse s)))
    0))
