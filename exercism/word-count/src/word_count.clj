(ns word-count
  "Given a phrase, count the occurrences of each word in that phrase."
  (:require [clojure.string :as string]))

(defn word-count [s]
  (-> s
      .toLowerCase
      (string/replace #"[^a-z0-9]" " ")
      (string/split #"\s+")
      frequencies))
