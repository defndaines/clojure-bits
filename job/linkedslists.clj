; 2.1 Write code to remove duplicates from an unsorted linked list.
(defn rm-dups
 [coll]
 (loop [l coll, seed '()]
  (cond (empty? l) seed
        (some #(= (first l) %) seed) (recur (rest l) seed)
        :else (recur (rest l) (conj seed (first l))))))
