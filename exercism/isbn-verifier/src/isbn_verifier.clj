(ns isbn-verifier
  "The ISBN-10 verification process is used to validate book identification
  numbers. These normally contain dashes and look like: 3-598-21508-8 ISBN

  The ISBN-10 format is 9 digits (0 to 9) plus one check character (either a
  digit or an X only). In the case the check character is an X, this
  represents the value '10'. These may be communicated with or without
  hyphens, and can be checked for their validity by the following formula:

  (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
      * 2 + x10 * 1) mod 11 == 0

  If the result is 0, then it is a valid ISBN-10, otherwise it is invalid.

  See: https://en.wikipedia.org/wiki/International_Standard_Book_Number")

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
