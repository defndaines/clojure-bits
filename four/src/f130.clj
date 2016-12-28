(ns f130)

(fn of [node tree]
	(let [node-name (fn [tree]
										(when (sequential? tree)
											(first tree)))

				path-to (fn path-to
									[path node tree]
									(let [nname (node-name tree)
												here (conj path nname)]
										(if (= node nname)
											here
											(mapcat (partial path-to here node)
															(rest tree)))))

				tree-from (fn [node tree]
										(if (= node (node-name tree))
											tree
											(mapcat (partial tree-from node)
															(rest tree))))

				rm-node (fn [node tree]
									(if-let [nname (node-name tree)]
										(when (not= nname node)
											(let [sibs (remove nil? (map #(rm-node node %) (rest tree)))]
												(concat [nname] sibs)))))]

		(when (seq tree)
			(let [[n parent _] (reverse (path-to [] node tree))]
				(if parent
					(concat (tree-from node tree)
									[(of parent (rm-node node tree))])
					(when n
						(tree-from node tree)))))))

(= '(n)
	 (__ 'n '(n)))

(= '(a (t (e)))
	 (__ 'a '(t (e) (a))))

(= '(e (t (a)))
	 (__ 'e '(a (t (e)))))

(= '(a (b (c)))
	 (__ 'a '(c (b (a)))))

(= '(d
		 (b
			(c)
			(e)
			(a
			 (f
				(g)
				(h)))))
	 (__ 'd '(a
						(b
						 (c)
						 (d)
						 (e))
						(f
						 (g)
						 (h)))))

(= '(c
		 (d)
		 (e)
		 (b
			(f
			 (g)
			 (h))
			(a
			 (i
				(j
				 (k)
				 (l))
				(m
				 (n)
				 (o))))))
	 (__ 'c '(a
						(b
						 (c
							(d)
							(e))
						 (f
							(g)
							(h)))
						(i
						 (j
							(k)
							(l))
						 (m
							(n)
							(o))))))

(def __
	(fn [n [f & r :as c]]
		(if (= n f)
			c
			(let [branch (vec (first (filter #(some #{n} (flatten %)) r)))
						siblings (remove #{branch} r)]
				(recur n (conj branch (cons f siblings))))))
	)
