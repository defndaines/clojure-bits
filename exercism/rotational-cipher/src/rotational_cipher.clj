(ns rotational-cipher
  "Create an implementation of the rotational cipher, also sometimes called
  the Caesar cipher.

  The Caesar cipher is a simple shift cipher that relies on transposing all
  the letters in the alphabet using an integer key between 0 and 26. Using a
  key of 0 or 26 will always yield the same output due to modular arithmetic.
  The letter is shifted for as many values as the value of the key.

  The general notation for rotational ciphers is ROT + <key>. The most
  commonly used rotational cipher is ROT13.")

(def lower-case "abcdefghijklmnopqrstuvwxyz")
(def upper-case "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(defn rotate-on
  "Given a string of `letters`, check if the supplied character `ch` is one of
  the letters, and if so, rotate it the given amount."
  [letters amt ch]
  (let [index (.indexOf letters (int ch))]
    (when (< -1 index)
      (.charAt
        letters
        (mod (+ amt (.indexOf letters (int ch))) 26)))))

(defn rotate-fn
  "Generate a rotate function which will take a character `ch` and rotate it the
  given amount. If the supplied character is not alphabetic, it will be passed
  back as is."
  [amt]
  (fn [ch]
    (or
      (rotate-on lower-case amt ch)
      (rotate-on upper-case amt ch)
      ch)))

(defn rotate [phrase amt]
  (apply str (map (rotate-fn amt) phrase)))
