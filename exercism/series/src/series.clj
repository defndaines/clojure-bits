(ns series
  "Given a string of digits, output all the contiguous substrings of length n
  in that string in the order that they appear.

  Note that these series are only required to occupy adjacent positions in the
  input; the digits need not be numerically consecutive.")

(defn slices [string length]
  (if (< 0 length)
    (map
      (partial apply str)
      (partition length 1 string))
    [""]))
