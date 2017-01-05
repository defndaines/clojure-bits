(ns kindergarten-garden)

(def plants
  {\R :radishes
   \C :clover
   \G :grass
   \V :violets})

(def default-students
  ["Alice" "Bob" "Charlie" "David"
   "Eve" "Fred" "Ginny" "Harriet"
   "Ileana" "Joseph" "Kincaid" "Larry"])

(defn garden
  ([s] (garden s default-students))
  ([s scholars]
   (let [students (map #(keyword (clojure.string/lower-case %)) (sort scholars))
         pupils (mapcat vector students students)
         rows (map #(map plants %) (clojure.string/split-lines s))]
     (apply #(merge-with concat %1 %2)
            (for [row rows]
              (reduce
                (fn [acc [s p]]
                  (assoc acc s (conj (get acc s []) p)))
                {}
                (map vector pupils row)))))))