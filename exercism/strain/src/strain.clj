(ns strain
  "Implement the keep and discard operation on collections. Given a collection
  and a predicate on the collection's elements, keep returns a new collection
  containing those elements where the predicate is true, while discard returns
  a new collection containing those elements where the predicate is false.")

(defn retain [pred coll]
  (reduce
    (fn [acc e] (if (pred e) (conj acc e) acc))
    []
    coll))

(defn discard [pred coll]
  (retain (complement pred) coll))
