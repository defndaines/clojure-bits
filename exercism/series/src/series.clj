(ns series)

(defn slices [string length]
  (if (< 0 length)
    (map
      (partial apply str)
      (partition length 1 string))
    [""]))
