(ns grains)

(defn square [n]
  (if (> 64 n)
    (bit-shift-left 1 (dec n))
    (*' 2 (square (dec n)))))

(defn total []
  (dec (square 65)))