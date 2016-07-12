(ns multimap)
;; A multimap is any hash-map where the value is a sequence.

(defn sort-values
  "Returns a sorted map of the items in coll, sorted by key and also by each value."
  [coll]
  (into (sorted-map) (for [[k v] coll] [k (sort v)])))

(defn multimap-invert
  "Returns the map with each value mapped to the keys.
  An optional transform function can be passed to apply to each value."
  ([coll] (multimap-invert identity coll))
  ([tr coll]
   (reduce
     (fn [acc [k vs]]
       (reduce (fn [a e] (assoc a e k)) acc (map tr vs)))
     {}
     coll)))
