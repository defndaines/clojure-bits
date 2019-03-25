(ns crypto-square
  "Implement the classic method for composing secret messages called a square
  code.

  Given an English text, output the encoded version of that text.

  First, the input is normalized: the spaces and punctuation are removed from
  the English text and the message is downcased.

  Then, the normalized characters are broken into rows. These rows can be
  regarded as forming a rectangle when printed with intervening newlines.

  The plaintext should be organized in to a rectangle. The size of the
  rectangle (r x c) should be decided by the length of the message, such that
  c >= r and c - r <= 1, where c is the number of columns and r is the number
  of rows.

  The coded message is obtained by reading down the columns going left to
  right.

  Output the encoded text in chunks that fill perfect rectangles (r X c),
  with c chunks of r length, separated by spaces. For phrases that are n
  characters short of the perfect rectangle, pad each of the last n chunks
  with a single trailing space.")

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
