(ns octal)

(def octals {\0 0
             \1 1
             \2 2
             \3 3
             \4 4
             \5 5
             \6 6
             \7 7})

(defn to-decimal [s]
  (if (every? (set (keys octals)) s)
    (first
      (reduce
        (fn [[sum base] e]
          [(+ sum (* base (get octals e)))
           (* base 8)])
        [0 1]
        (reverse s)))
    0))