(ns grade-school)

(defn grade [db g]
  (get db g []))

(defn add [db student g]
  (assoc db g (conj (grade db g) student)))

(defn sorted [db]
  (into (sorted-map) (for [[k v] db] [k (sort v)])))