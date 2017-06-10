;; Utility functions for generating random data of a given type.
(ns random.core)

(defn uuid
  "Generate a random UUID string, 32 hexadecimal digits separated by dashes."
  []
  (str (java.util.UUID/randomUUID)))

(defn id
  "Generate a random system identifier, typically a 4–5 digit number."
  []
  (rand-int 10000))

(defn ip
  "Generate a random IPv4 address, without regard to reserved IPs."
  []
  (clojure.string/join "." (repeatedly 4 #(rand-int 256))))

(def ^:private alpha-num "abcdefghijklmnopqrstuvwxyz0123456789")

(defn- rand-char
  "Generate a random, lowercase alpha-numeric character."
  []
  (.charAt alpha-num (rand-int 36)))

(defn url
  "Generate a random, vaguely plausible URL in the form \"https://prefix.company.com\"."
  []
  (let [prefix (apply str (repeatedly (+ 2 (rand-int 8)) rand-char))
        company (apply str (repeatedly (+ 4 (rand-int 25)) rand-char))]
    (apply str "https://" prefix "." company ".com")))

(defn bool
  "Generate a random boolean."
  []
  (if (zero? (rand-int 2)) false true))

(def ^:private hex-num "0123456789abcdef")

(defn- rand-hex
  "Generate a random hexidecimal digit as a character."
  []
  (.charAt hex-num (rand-int 16)))

(defn hex-str
  "Generate a random hexidecimal string 24 characters long, good for simulating a cookie."
  []
  (apply str (repeatedly 24 rand-hex)))
