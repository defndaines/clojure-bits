(ns accumulate)

(defn accumulate [f coll]
  (if (seq coll)
    (cons (f (first coll))
          (lazy-seq (accumulate f (rest coll))))
    coll))
