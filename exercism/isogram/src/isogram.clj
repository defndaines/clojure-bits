(ns isogram)

(def uniq? (partial apply distinct?))

(defn isogram? [str]
  (-> str
      (clojure.string/replace #"\P{IsAlphabetic}" "")
      clojure.string/lower-case
      uniq?))