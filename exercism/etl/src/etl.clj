(ns etl
  "We are going to do the Transform step of an Extract-Transform-Load.

  We're going to extract some scrabble scores from a legacy system.")

(defn transform [m]
  (reduce
    (fn [acc [points words]]
      (let [ws (map clojure.string/lower-case words)]
        (reduce (fn [a e] (assoc a e points)) acc ws)))
    {} m))
