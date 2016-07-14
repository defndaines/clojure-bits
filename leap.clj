(ns leap)
;; Spoiler Alert: This was for the exercism.io "leap" problem.
;; Archiving here as a personal example of condp.

(defn ^:private divisible-by? [n year]
  (zero? (rem year n)))

(defn leap-year? [year]
  (condp divisible-by? year
    400 true
    100 false
    4 true
    false))
