(ns leap)

(defn leap-year? [year]
  (cond
    (< 0 (rem year 4)) false
    (zero? (rem year 400)) true
    (zero? (rem year 100)) false
    :else true))
