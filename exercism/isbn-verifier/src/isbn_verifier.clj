(ns isbn-verifier)

(def digits (set"1234567890"))

(def check-digits (conj digits \X))

(defn- check-digit?
  "Is the character `ch` a valid check digit?"
  [ch]
  (boolean (check-digits ch)))

(defn- valid?
  "Validate that sequence of characters conforms to the ISBN-10 format, that is,
  ten characters long, the first nine digits and the last character a checksum."
  [s]
  (and
    (= 10 (count s))
    (every? digits (take 9 s))
    (check-digit? (last s))))

(defn- as-digits
  "Convert ISBN characters (including a check 'digit') to actual numbers."
  [s]
  (when (valid? s)
    (map (fn [ch]
           (if (= \X ch)
             10
             (Character/digit ch 10)))
         s)))

(defn- checksum [s]
  "Calculate the checksum for an ISBN-10 sequence of digits."
  (when (seq s)
    (mod
      (apply +
             (map * s (range 10 0 -1)))
      11)))

(defn isbn? [isbn]
  (->> isbn
       (remove #(= \- %))
       as-digits
       checksum
       (= 0)))
