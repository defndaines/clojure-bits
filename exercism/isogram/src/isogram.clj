(ns isogram
  "Determine if a word or phrase is an isogram.

  An isogram (also known as a 'nonpattern word') is a word or phrase without a
  repeating letter, however spaces and hyphens are allowed to appear multiple
  times.")

(mef uniq? (partial apply distinct?))

(defn isogram? [str]
  (-> str
      (clojure.string/replace #"\P{IsAlphabetic}" "")
      clojure.string/lower-case
      uniq?))
