(ns rotational-cipher)

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
