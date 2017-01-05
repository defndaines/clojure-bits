(ns change)

(defn ^:private shortest
  "Get the shortest, non-empty sequence."
  [left right]
  (if (< 0 (count left) (count right)) left right))

(defn shortest-combo
  "Find the shortest comibination of coins."
  [amt coins]
  (let [cs (sort > coins)]
    (reduce
      shortest '()
      (reduce
        (fn [acc e]
          (conj acc
                (concat
                  (repeat (quot amt e) e)
                  (shortest-combo (rem amt e) (second (partition-by #(> e %) cs))))))
        '()
        cs))))

(defn issue [amt coins]
  (when (< amt 0) (throw (IllegalArgumentException. "cannot change")))
  (when (< 0 amt (apply min coins)) (throw (IllegalArgumentException. "cannot change")))
  (reverse (shortest-combo amt coins)))
