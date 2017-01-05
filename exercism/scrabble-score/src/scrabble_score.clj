(ns scrabble-score)

(def scores {\D 2 \G 2
             \B 3 \C 3 \M 3 \P 3
             \F 4 \H 4 \V 4 \W 4 \Y 4
             \K 5
             \J 8 \X 8
             \Q 10 \Z 10})

(defn score-word [word]
  (reduce
    (fn [acc e] (+ acc (get scores e 1)))
    0
    (clojure.string/upper-case word)))

(def score-letter score-word)