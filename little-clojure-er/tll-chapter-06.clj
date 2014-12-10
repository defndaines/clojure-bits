; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

;Chapter 6 - Page 88
;Note that (non-atom?) is (seq?) in Clojure core.
(defn leftmost
  "Return the first atom of l where l can be a list of lists"
  [l]
  (cond
    (null? l) '()
    (seq? (first l))
      (leftmost (first l))
    :else (first l)))

;Chapter 6 - Page 88
;(not) is in Clojure core
(defn is-not
  "not"
  [b]
  (cond
    b false
    :else true))

;Chapter 6 - Page 89
(defn rember*
  "Return a new list of atoms, removing all occurrences of a from list l which may contain other lists"
  [a l]
  (cond
    (null? l) '()
    (seq? (first l))
        (cons (rember* a (first l)) (rember* a (rest l)))
    :else (cond
      (= (first l) a) (rember* a (rest l))
      :else (cons (first l) (rember* a (rest l))))))

;Chapter 6 - Page 90
(defn insertR*
  "Builds a list with new inserted to the right of every occurrence of old"
  [new old l]
  (cond
    (null? l) '()
    (seq? (first l))
        (cons (insertR* new old (first l)) (insertR* new old (rest l)))
    :else (cond
      (= (first l) old) (cons old (cons new (insertR* new old (rest l))))
      :else (cons (first l) (insertR* new old (rest l))))))

;Chapter 6 - Page 92
(defn occur*
  "Count the number of times an atom a appears in list l"
  [a l]
  (cond
    (null? l) 0
    (seq? (first l))
        (+ (occur* a (first l)) (occur* a (rest l)))
    :else (cond
      (= (first l) a) (inc (occur* a (rest l)))
      :else (occur* a (rest l)))))

;Chapter 6 - Page 93
(defn subst*
  "Replace every occurrence of old in the list l with the atom new"
  [new old l]
  (cond
    (null? l) '()
    (seq? (first l))
        (cons (subst* new old (first l)) (subst* new old (rest l)))
    :else (cond
      (= (first l) old) (cons new (subst* new old (rest l)))
      :else (cons (first l) (subst* new old (rest l))))))

;Chapter 6 - Page 94
(defn insertL*
  [new old l]
  (cond
    (null? l) '()
    (seq? (first l))
        (cons (insertL* new old (first l)) (insertL* new old (rest l)))
    :else (cond
      (= (first l) old) (cons new (cons old (insertL* new old (rest l))))
      :else (cons (first l) (insertL* new old (rest l))))))

;Chapter 6 - Page 94
(defn member*
  "Evaluate whether a is a member of list l"
  [a l]
  (cond
    (null? l) false
    (seq? (first l))
        (or (member* a (first l)) (member* a (rest l)))
    :else (cond
      (= (first l) a) true
      :else (member* a (rest l)))))

;Chapter 6 - Page 95
(defn member*
  "Evaluate whether a is a member of list l (without using (seq?))"
  [a l]
  (cond
    (null? l) false
    (atom? (first l))
        (or
          (= (first l) a)
          (member* a (rest l)))
    :else (or
            (member* a (first l))
            (member* a (rest l)))))


;Chapter 6 - Page 97
(defn eqlist?
  "Returns true is the two lists are structurally the same"
  [l1 l2]
  (cond
    (and (null? l1) (null? l2)) true
    (or (null? l1) (null? l2)) false
    (and (seq? (first l1)) (seq? (first l2)))
      (and (eqlist? (first l1) (first l2))
        (eqlist? (rest l1) (rest l2)))
    (or (seq? (first l1)) (seq? (first l2))) false
    :else (and (eqan? (first l1) (first l2))
      (eqlist? (rest l1) (rest l2)))))

;Chapter 6 - Page 98
(defn equals?
  "Determine if two S-expressions are structurally the same"
  [s1 s2]
  (cond
    (and (atom? s1) (atom? s2)) (eqan? s1 s2)
    (and (seq? s1) (seq? s2)) (eqlist? s1 s2)
    :else false))

;Chapter 6 - Page 98
(defn eqlist?
  "Returns true is the two lists are structurally the same"
  [l1 l2]
  (cond
    (and (null? l1) (null? l2)) true
    (or (null? l1) (null? l2)) false
    :else (and
      (equals? (first l1) (first l2))
      (eqlist? (rest l1) (rest l2)))))

;Chapter 6 - Page 98
(defn rember
  "Remove an S-expression from the list l"
  [s l]
  (cond
    (null? l) '()
    (seq? (first l)) (cond
      (equals? (first l) s) (rest l)
      :else (cons (first l) (rember s (rest l))))
    :else (cond
      (equals? (first l) s) (rest l)
      :else (cons (first l) (rember s (rest l))))))

;Chapter 6 - Page 99
(defn rember
  "Remove an S-expression from the list l (simplified)"
  [s l]
  (cond
    (null? l) '()
    :else (cond
      (equals? (first l) s) (rest l)
      :else (cons (first l) (rember s (rest l))))))

;Chapter 6 - Page 99
(defn rember
  "Remove an S-expression from the list l (even more simplified)"
  [s l]
  (cond
    (null? l) '()
    (equals? (first l) s) (rest l)
    :else (cons (first l) (rember s (rest l)))))

;Chapter 6 - Page 100
(defn insertL*
  "Insert new before every occurrence of old in list l"
  [new old l]
  (cond
    (null? l) '()
    (seq? (first l))
        (cons (insertL* new old (first l)) (insertL* new old (rest l)))
    (eqan? (first l) old)
        (cons new (cons old (insertL* new old (rest l))))
    :else (cons (first l) (insertL* new old (rest l)))))

