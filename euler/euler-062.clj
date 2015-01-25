(ns euler-062.core)

;; The cube, 41063625 (345^3), can be permuted to produce two other cubes:
;; 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube
;; which has exactly three permutations of its digits which are also cube.
;; Find the smallest cube for which exactly five permutations of its digits are
;; cube.

(defn lowest-number-by-digits
  "Returns the lowest number with x digits."
  [x]
  (if (= 1 x) 1
    (Math/pow 10 (dec x))))

(defn next-cube
  "Returns the next integer with a cube greater than or equal to the provided value."
  ;; Attempting to cube any values returned by x > 19 will fail.
  [x]
  (long (Math/ceil (Math/cbrt x))))

(defn digits-in
  "Returns the number of digits in the value provided."
  [x]
  (count (seq (str x))))

(defn cube [x]
  ;; Would need to use *' to handle values > 2097151.
  (* x x x))

(defn cubes
  "Lazy sequence of cubes, starting with the cube of x."
  [x]
  (cons (cube x)
        (lazy-seq (cubes (inc x)))))

(defn cubes-by-digits
  "Returns a sequence of all cubes with x digits."
  [x]
  (take-while #(= x (digits-in %))
              (cubes (next-cube (lowest-number-by-digits x)))))

(defn find-candidates
  "Find candidate values from numbers in s where permutations contain exactly x cubes."
  [x s]
  (flatten (map second
                ;; Only keep those which contain the desired number of elements.
                (filter #(= x (count (second %)))
                        ;; Group all cubes by their sorted character sequences.
                        (group-by #(apply str (sort (seq (str %)))) s)))))

(defn euler-062
  "Find the smallest cube for which exactly x permutations of its digits are also cubes."
  [x]
  (loop [n 1]
    (let [n-cubes (cubes-by-digits n)
          candidates (find-candidates x n-cubes)]
      (if (seq candidates)
        (apply min candidates)
        (recur (inc n))))))

;; Potential optimizations:
;; * Start the "n" value in the solver at a higher value.
;;     With 1, clocks in at about 80 msecs right now on my laptop, and can cut
;;     nearly in half by seeding with 12.
;; * Inlining everything might be faster, but I wanted to break things up to be
;;     cleaner to read.
