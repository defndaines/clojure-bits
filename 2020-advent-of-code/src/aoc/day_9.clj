(ns aoc.day-9
  "--- Day 9: Encoding Error ---
  ...
  What is the first number that does not have this property?
  --- Part Two ---
  ...
  What is the encryption weakness in your XMAS-encrypted list of numbers?")

(defn- valid-numbers
  [nums]
  (loop [valid #{}
         l nums]
    (let [n (first l)
          r (rest l)]
      (if (number? n)
        (recur
          (reduce
            (fn [acc e] (conj acc (+ n e)))
            valid
            r)
          r)
        valid))))

(defn valid?
  "Is `n` the sum of two different numbers in the `preamble`?"
  [preamble n]
  ((valid-numbers preamble) n))

(defn find-invalid
  "Find invalid numbers in a sequence."
  [nums pre]
  (loop [preamble (vec (take pre nums))
         r (drop pre nums)]
    (let [n (first r)]
      (if (valid? preamble n)
        (recur (conj (vec (rest preamble)) n)
               (rest r))
        n))))

(defn find-xmas-weakness
  [nums n]
  (let [invalid n
        bust (inc invalid)]
    (loop [l nums]
      (let [sum (atom 0)
            s (take-while (fn [i] (swap! sum + i) (< @sum bust)) l)
            total (reduce + s)]
        (if (= invalid total)
          (let [sorted (sort s)]
            (+ (first sorted) (last sorted)))
          (recur (rest l)))))))
