(ns etl)

(defn transform [m]
  (reduce
    (fn [acc [points words]]
      (let [ws (map clojure.string/lower-case words)]
        (reduce (fn [a e] (assoc a e points)) acc ws)))
    {} m)) 
