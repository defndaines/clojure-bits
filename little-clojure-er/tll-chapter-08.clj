; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

;Chapter 8 - Page 119
;Uses (member?) from Chapter 2
(defn is-set?
  "Determine if lat is a set, i.e., contains no duplicate atoms"
  [lat]
  (cond
    (null? lat) true
    (member? (first lat) (rest lat)) false
    :else (is-set? (rest lat))))

;Chapter 8 - Page 120
; Requires (member?) from Chapter 2
(defn makeset
  "Create a set of unique items out of list of atoms"
  [lat]
  (cond
    (null? lat) '()
    (member? (first lat) (rest lat))
        (makeset (rest lat))
    :else (cons (first lat) (makeset (rest lat)))))

;Chapter 8 - Page 120
; Alternate version. Requires (multirember) from Chapter 5
(defn makeset
  "Create a set of unique items out of list of atoms"
  [lat]
  (cond
    (null? lat) '()
    :else (cons (first lat) (makeset (multirember (first lat) (rest lat))))))

;Chapter 8 - Page 121
(defn subset?
  "True if set1 is a subset of set2"
  [set1 set2]
  (cond
    (null? set1) true
    (member? (first set1) set2)
        (subset? (rest set1) set2)
    :else false))

;Chapter 8 - Page 122
; Alternate version using (and)
(defn subset?
  "True if set1 is a subset of set2"
  [set1 set2]
  (cond
    (null? set1) true
    :else (and (member? (first set1) set2)
        (subset? (rest set1) set2))))

;Chapter 8 - Page 122
(defn eqset?
  "True if set1 and set2 contain all the same atoms"
  [set1 set2]
  (and (subset? set1 set2) (subset? set2 set1)))

;Chapter 8 - Page 123
(defn intersect?
  "True of set1 and set2 contain common elements"
  [set1 set2]
  (cond
    (null? set1) false
    :else (or (member? (first set1) set2) 
        (intersect? (rest set1) set2))))

;Chapter 8 - Page 124
(defn intersect
  "Return a set of the common elements between set1 and set2"
  [set1 set2]
  (cond
    (null? set1) '()
    (member? (first set1) set2)
        (cons (first set1) (intersect (rest set1) set2))
    :else (intersect (rest set1) set2)))

;Chapter 8 - Page 124
; Alternate version using (not (member?))
(defn intersect
  "Return a set of the common elements between set1 and set2"
  [set1 set2]
  (cond
    (null? set1) '()
    (not (member? (first set1) set2))
        (intersect (rest set1) set2)
    :else (cons (first set1) (intersect (rest set1) set2))))

;Chapter 8 - Page 125
(defn union
  "Generate a set containing all unique elements of set1 and set2"
  [set1 set2]
  (cond
    (null? set1) set2
    (member? (first set1) set2)
        (union (rest set1) set2)
    :else (cons (first set1) (union (rest set1) set2))))

;Chapter 8 - Page 125
; function formerly known as (xxx)
(defn get-complement
  "Return all the atoms of set1 that are not in set2"
  [set1 set2]
  (cond
    (null? set1) '()
    (member? (first set1) set2)
        (get-complement (rest set1) set2)
    :else (cons (first set1) (get-complement (rest set1) set2))))

;Chapter 8 - Page 126
(defn intersectall
  "Return a set containing the unique elements present in all sets in l-set"
  [l-set]
  (cond
    (null? (rest l-set)) (first l-set)
    :else (intersect (first l-set) (intersectall (rest l-set)))))

;Chapter 8 - Page 127
(defn first-in-pair
  "Return the first element of a pair"
  [p]
  (first p))

(defn second-in-pair
  "Return the second element of a pair"
  [p]
  (first (rest p)))

(defn build
  "Build a pair from two atoms"
  [a1 a2]
  (cons a1 (cons a2 '())))

(defn third
  "Return the third element in a list"
  [l]
  (first (rest (rest l))))

;Chapter 8 - Page 128
(defn fun?
  "Determine if a set of pairs contains unique first elements"
  [rel]
  (cond
    (null? rel) true
    (member? (first (first rel)) (firsts (rest rel))) false
    :else (fun? (rest rel))))

;Chapter 8 - Page 129
; Alternate version, using (is-set?)
(defn fun?
  "Determine if a set of pairs contains unique first elements"
  [rel]
  (is-set? (firsts rel)))

;Chapter 8 - Page 129
(defn revrel
  "Reverse the order of items in each pair in rel"
  [rel]
  (cond
    (null? rel) '()
    :else (cons (build (second-in-pair (first rel)) (first-in-pair (first rel)))
        (revrel (rest rel)))))

;Chapter 8 - Page 130
(defn fullfun?
  "True if the second elements in a set of pairs is unique, i.e., has a one-to-one relationship"
  [fun]
  (fun? (revrel fun)))
