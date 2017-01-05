(ns nucleotide-count)

(defn nucleotide-counts [nucleotide]
  (merge
    {\A 0, \T 0, \C 0, \G 0}
    (frequencies nucleotide)))

(defn count [base nucleotide]
  (if-let [n (get (nucleotide-counts nucleotide) base)]
    n
    (throw "invalid base")))
