(ns aoc.day-14
  "--- Day 14: Docking Data ---
  ...
  Execute the initialization program. What is the sum of all values left in
  memory after it completes?")

(defn apply-mask
  [n mask]
  (let [bin (Long/toBinaryString n)
        nb (apply str (concat (repeat (- (count mask) (count bin)) \0) bin))]
    (Long/parseUnsignedLong
      (apply str (map (fn [b m] (if (= \X m) b m)) nb mask))
      2)))

(defn- parse-line
  [line]
  (let [[_ mask] (re-find #"^mask = ([X10]+)$" line)]
    (if (seq mask)
      [:mask mask]
      (let [[_ loc n] (re-find #"^mem\[(\d+)] = (\d+)$" line)]
        [:update (Integer/parseInt loc) (Long/parseLong n)]))))

(defn run-program
  [prog]
  (dissoc
    (reduce
      (fn [acc e]
        (let [line (parse-line e)]
          (case (first line)
            :mask (assoc acc :mask (second line))
            :update (let [[_ loc n] line]
                      (assoc acc loc (apply-mask n (:mask acc)))))))
      {}
      prog)
    :mask))
