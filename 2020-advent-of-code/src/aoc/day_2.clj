(ns aoc.day-2
  "--- Day 2: Password Philosophy ---
  ...
  How many passwords are valid according to their policies?

  --- Part Two ---
  ...
  How many passwords are valid according to the new interpretation of the
  policies?")

(def line-regex #"(\d+)-(\d+) (\w): (\w+)")

(defn parse-line [s]
  (let [[_ from to ch password] (re-find line-regex s)]
    [(Integer/parseInt from)
     (Integer/parseInt to)
     (.charAt ch 0)
     password]))

(defn valid? [line]
  (let [[from to ch password] (parse-line line)]
    (<= from
        (count (filter #(= ch %) password))
        to)))

(defn toboggan-valid? [line]
  (let [[pos-1 pos-2 ch password] (parse-line line)]
    (= 1
       (count
         (filter identity
                 [(= ch (.charAt password (dec pos-1)))
                  (= ch (.charAt password (dec pos-2)))])))))
