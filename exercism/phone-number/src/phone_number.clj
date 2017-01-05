(ns phone-number)

(defn number [n]
  (apply str (apply concat (re-seq #"\p{Digit}+" n))))

(defn area-code [n]
  n)

(defn pretty-print [n]
  n)
