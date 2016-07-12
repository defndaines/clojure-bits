(ns pascals-triangle)
;; Spoiler Alert: This was for the exercism.io "pascals-triangle" problem.
;; Committing as a reference for creating lazy-seq.

(defn from-previous [row]
  (cons 1
        (map (partial apply +') (partition 2 1 [0] row))))

(defn triangle [row]
  (let [new-row (from-previous row)]
    (cons new-row (lazy-seq (lazy-triangle new-row)))))
