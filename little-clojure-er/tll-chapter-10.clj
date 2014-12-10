; The Little LISPer (Trade Edition, 1987)
; Translated to Clojure

;Chapter 10 - Page 158
(defn lookup-in-entry
  [name entry entry-f]
  (lookup-in-entry-help name (first entry) (second entry) entry-f))

(defn lookup-in-entry-help
  [name names values entry-f]
  (cond
    (null? names) (entry-f name)
    (= (first names) name) (first values)
    :else (lookup-in-entry-help name (rest names) (rest values) entry-f)))

;Chapter 10 - Page 159
(defn lookup-in-table
 [name table table-f]
 (cond
  (null? table) (table-f name)
  :else (lookup-in-entry name (first table)
    (fn [name] lookup-in-table name (rest table) table-f))))

;Chapter 10 - Page 162
(defn expression-to-action
 [e]
 (cond
  (atom? e) (atom-to-action e)
  :else (list-to-action e)))

(defn atom-to-action
 [e]
 (number? e) *self-evaluating
 :else *identifier)

(defn list-to-action
 [e]
 (cond
  (atom? (first e)) (cond
    (= (first e) (quote quote)) *quote
    (= (first e) (quote lambda)) *lambda
    (= (first e) (quote cond)) *cond
    :else *application)
  :else *application))

(defn value
 [e]
 (meaning e '()))

(defn meaning
 [e table]
 ((expression-to-action e) e table))

(defn *self-evaluating
 [e table]
 e)

(defn *quote
 [e table]
 (text-of-quotation e))

(def text-of-quotation second)

(defn *identifier
 [e table]
 (lookup-in-table e table initial-table))

(defn initial-table
 [name]
 (cond
  (= name 'true) true
  (= name 'nil) nil
  :else (build 'primitive name)))

(defn *lambda
 [e table]
 (build 'non-primitive (cons table (rest e))))


