(ns wordy
  "Parse and evaluate simple math word problems returning the answer as an
  integer.")

(def ops #" *(plus|minus|multiplied by|divided by) *")

(defn parse-tokens [tokens]
  (reduce
    (fn [acc e]
      (case e
        "plus" (conj acc +)
        "minus" (conj acc -)
        "multiplied" (conj acc *)
        "divided" (conj acc /)
        "by" acc
        (conj acc (Integer/parseInt e))))
    []
    tokens))

(defn evaluate [s]
  (if-not (clojure.string/starts-with? s "What is ")
    (throw (IllegalArgumentException.)))
  (let [tokens (drop 2 (clojure.string/split s #"\s+|\?"))]
    (loop [stack (parse-tokens tokens)]
      (if (= 1 (count stack))
        (first stack)
        (let [[x op y & more] stack]
          (recur (conj more (op x y))))))))
