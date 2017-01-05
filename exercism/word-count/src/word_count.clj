(ns word-count
  (:require [clojure.string :as str]))

(defn word-count [s]
  (-> s
      .toLowerCase
      (str/replace #"[^a-z0-9]" " ")
      (str/split #"\s+")
      frequencies))
