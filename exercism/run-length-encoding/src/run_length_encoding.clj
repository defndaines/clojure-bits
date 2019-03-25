(ns run-length-encoding
  "Implement run-length encoding and decoding.

  Run-length encoding (RLE) is a simple form of data compression, where runs
  (consecutive data elements) are replaced by just one data value and count.
  https://en.wikipedia.org/wiki/Run-length_encoding")

(defn- encode
  "RLE encoding recuding function."
  [acc e]
  (let [n (count e)
        ch (first e)]
    (if (= 1 n)
      (conj acc ch)
      (conj acc n ch))))

(defn run-length-encode
  "Encodes a string with run-length-encoding."
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (reduce encode [])
       (apply str)))


(defn digit? [ch]
  (Character/isDigit ch))

(defn read-number
  "Convert a sequence of digit characters to an integer or return 1 if empty."
  [s]
  (if (seq s)
    (Integer. (apply str s))
    1))

(defn run-length-decode
  "Decodes a run-length-encoded string."
  [cipher-text]
  (loop [acc []
         input cipher-text]
    (if (seq input)
      (let [[n [ch & butt]] (split-with digit? input)
            times (read-number n)]
        (recur (concat acc (repeat times ch)) butt))
      (apply str acc))))
