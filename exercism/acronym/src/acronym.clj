(ns acronym)

(defn acronym [s]
  (->>
    (clojure.string/split s #"(\p{Punct}|\p{Space}|\p{Lower}(?=\p{Upper}))+")
    (map first)
    (apply str)
    clojure.string/upper-case))
