(ns crypto-square)

(defn normalize-plaintext [s]
  (clojure.string/lower-case
    (clojure.string/replace s #"\W" "")))

(defn square-size [s]
  (int (Math/ceil (Math/sqrt (count s)))))

(defn plaintext-segments [s]
  (let [phrase (normalize-plaintext s)
        size (square-size phrase)]
    (->> phrase
         (partition-all size)
         (map (partial apply str)))))

(defn transpose [coll]
  ;; (apply mapv vector coll) doesn't work because the last element is shorter.
  (loop [acc []
         c coll]
    (if (seq (first c))
      (recur (conj acc (for [row c] (first row)))
             (map rest c))
      acc)))

(defn normalize-ciphertext [s]
  (->> s
       plaintext-segments
       transpose
       (map (partial apply str))
       (clojure.string/join \space)))

(defn ciphertext [s]
  (normalize-plaintext (normalize-ciphertext s)))
