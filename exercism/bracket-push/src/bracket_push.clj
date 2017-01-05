(ns bracket-push)

(defn ^:private pop-on-match [coll ch]
  (if (= (peek coll) ch)
    (pop coll)
    (conj coll \!)))

(defn valid? [s]
  (empty?
    (reduce
      (fn [acc e]
        (case e
          \[ (conj acc e)
          \{ (conj acc e)
          \( (conj acc e)
          \] (pop-on-match acc \[) 
          \} (pop-on-match acc \{)
          \) (pop-on-match acc \()
          acc))
      '() s)))