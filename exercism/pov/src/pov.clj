(ns pov)

(defn ^:private node-name [tree]
  (when (sequential? tree)
    (first tree)))

(defn ^:private path-to
  ([node tree] (path-to [] node tree))
  ([path node tree]
   (let [nname (node-name tree)
         here (conj path nname)]
     (if (= node nname)
       (conj path nname)
       (mapcat (partial path-to here node)
               (rest tree))))))

;; Doesn't reduce path below root, which isn't tested.
(defn path-from-to [node parent tree]
  (let [to-node (path-to node tree)
        to-parent (path-to parent tree)]
    (when (and (seq to-node) (seq to-parent))
      (concat (reverse to-node) (rest to-parent)))))

(defn ^:private tree-from [node tree]
  (let [nname (node-name tree)]
    (if (= node nname)
      tree
      (mapcat (partial tree-from node)
              (rest tree)))))

(defn ^:private rm-node [node tree]
  (when (seq tree)
    (let [nname (node-name tree)]
      (when (not= nname node)
        (let [sibs (remove nil? (map #(rm-node node %) (rest tree)))]
          (if (seq sibs)
            (concat [nname] sibs)
            [nname]))))))

(defn of [node tree]
  (when (seq tree)
    (let [[n parent _] (reverse (path-to node tree))]
      (if parent
        (concat (tree-from node tree)
                [(of parent (rm-node node tree))])
        (when n
          (tree-from node tree))))))
