;; Technique for avoiding strings with a forbidden character.
(def gen-file-name
  (get/fmap #(clojure.string/replace % "/" "")
            gen/string-ascii))

;; Generate octal permissions.
(def gen-permissions-octal
  (gen/fmap #(format "%03o" %)
            (gen/large-integer {:min 0 :max 0777})))

;; Alternative for date-time generation.
(def gen-date-time-components
  (gen/hash-map
    :year (gen/fmap #(+ % 1903) gen/int)
    :month (gen/large-integer* {:min 1 :max 12})
    :day (gen/large-integer* {:min 1 :max 31})
    :hour (gen/large-integer* {:min 0 :max 23})
    :minute (gen/large-integer* {:min 0 :max 59})
    :second (gen/large-integer* {:min 0 :max 59})
    :millis (gen/large-integer* {:min 0 :max 999})))

(defn construct-date-time
  [{:keys [year month day
           hour minute second millis]}]
  (try
    (java.time.Instant/parse
      (format "%04d-%02d-%02dT%02d:%02d:%02d.%03dZ"
              year month day
              hour minute second millis))
    (catch Exception e
      (java.time.Instant/parse
        (format "%04d-%02d-%02dT%02d:%02d:%02d.%03dZ"
                year month 28
                hour minute second millis)))))

(def gen-date-time-2
  (gen/fmap construct-date-time
            gen-date-time-components))

;; Generate width first, then use bind to generate
;; a collection of vectors with the same width.
(gen/let [width gen/nat
          rows (gen/vector
                 (gen/vector gen/large-integer
                             width))]
  rows)
