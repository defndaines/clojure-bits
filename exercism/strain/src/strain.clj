(ns strain)

(defn retain [pred coll]
  (reduce
    (fn [acc e] (if (pred e) (conj acc e) acc))
    []
    coll))

(defn discard [pred coll]
  (retain (complement pred) coll))