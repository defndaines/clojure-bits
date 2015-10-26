;;; Clojure implementation of the {0,1,?} parser.

(defn combine [acc e]
  (if (= e \?)
    (for [l acc n [\0 \1]] (conj l n))
    (for [l acc] (conj l e))))

(defn translate [phrase]
  (map #(apply str %)
       (reduce combine [(vec "")] (vec phrase))))
