(ns pascals-triangle
  "Compute Pascal's triangle up to a given number of rows.

  In Pascal's Triangle each number is computed by adding the numbers to the
  right and left of the current position in the previous row.")

(defn from-previous [row]
  (cons 1
        (map (partial apply +') (partition 2 1 [0] row))))

(defn lazy-triangle [row]
  (let [new-row (from-previous row)]
    (cons new-row (lazy-seq (lazy-triangle new-row)))))

(def triangle (lazy-triangle []))

(defn row [n]
  (nth triangle (dec n)))
