(ns binary-search-tree
  "Insert and search for numbers in a binary tree.")

(defn singleton [v]
  {:value v})

(defn value [tree]
  (:value tree))

(defn left [tree]
  (:left tree))

(defn right [tree]
  (:right tree))

(declare add-node)

(defn insert [v tree]
  (if (seq tree)
    (if (<= v (:value tree))
      (add-node tree :left v)
      (add-node tree :right v))
    (singleton v)))

(defn ^:private add-node [tree pos v]
  (if-let [node (pos tree)]
    (assoc tree pos (insert v node))
    (assoc tree pos (singleton v))))

(defn from-list [l]
  (reduce (fn [acc e] (insert e acc)) {} l))

(defn to-list [tree]
  (when (seq tree)
    (concat
      (to-list (:left tree))
      (list (:value tree))
      (to-list (:right tree)))))
