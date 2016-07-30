(ns data
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

;;; This is a collection of functions for doing confusion matrix and other
;;; machine learning result analysis. Some of it is generic, where other parts
;;; a quite specific to the calculations I've needed to do at work. In
;;; particular, we invert traditional positive-negative classification.
;;; Also, we run several models in parallel, per ID, and need to tease those
;;; apart for some analysis.

;;; WARNING: The code below is disorganized because it's mostly just for me
;;;          to use at the REPL.


;; recall
(defn tp-rate [stats]
  (let [tp (:tp stats)]
    (/ tp (max 1 (+ tp (:fn stats))))))

;; miss rate
(defn fn-rate [stats]
  (let [tp (:tp stats)]
    (/ (:fn stats) (max 1 (+ tp (:fn stats))))))

;; fallout
(defn fp-rate [stats]
  (let [fp (:fp stats)]
    (/ fp (max 1 (+ fp (:tn stats))))))

;; specificity
(defn tn-rate [stats]
  (let [tn (:tn stats)]
    (/ tn (max 1 (+ tn (:fp stats))))))

(defn accuracy [stats]
  (let [t (+ (:tp stats) (:tn stats))]
    (/ t
       (max 1 (+ t (:fp stats) (:fn stats))))))

(defn prevalence [stats]
  (let [t (+ (:tp stats) (:fn stats))]
    (/ t
       (max 1 (+ t (:fp stats) (:tn stats))))))

(defn precision [stats]
  (/ (:tp stats)
     (max 1 (+ (:tp stats) (:fp stats)))))

;; F-measure
(defn f1-score [stats]
  (let [dtp (* 2 (:tp stats))]
    (/ dtp
       (max 1 (+ dtp (:fp stats) (:fn stats))))))


;;; Area Under Curver [AUC] functions.

(defn trap-area
  "Trapezoid area."
  [x1 x2 y1 y2]
  (* (Math/abs (- x1 x2))
     (/ (+ y1 y2) 2)))

;; Algorithm adapted from "An introduction to ROC analysis" Tom Fawcett (2006)
;; which assumes that the input is already ordered by the value of "f" decreasing.
(defn iter
  "Area Under Curve [AUC] reduce function.
  The element is a vector of three elements: the ID of the model,
  whether the classifier example is positive (pos),
  and the probabilistic value (f).
  The accumulator tracks six values, aggregated area, the last f value (f'),
  negative examples (fp and fp'), and positive examples (tp and tp')."
  [[area f' fp fp' tp tp'] [_ pos f]]
  (if (= f' f)
    [area
     f'
     (if pos fp (inc fp))
     fp'
     (if pos (inc tp) tp)
     tp']
    ;; else
    [(+ area (trap-area fp fp' tp tp'))
     f
     (if pos fp (inc fp))
     fp
     (if pos (inc tp) tp)
     tp]))

;; Version of iter reduce function above which separates per ID.
(defn id-iter [acc [id pos f]]
  (let [id-acc (get acc id [0 Double/NEGATIVE_INFINITY 0 0 0 0])]
    (assoc acc id (iter id-acc, [id pos f]))))

;; Area Under the Curve [AUC], Receiver Operating Characteristic [ROC]
(defn auc [lines]
  (let [[a _ n fp' p tp'] (reduce iter [0 Double/NEGATIVE_INFINITY 0 0 0 0] lines)]
    (/ (+ a (trap-area n fp' n tp'))
       (* p n))))

(defn id-auc [lines]
  ;; TODO
  )

(defn w-acc [m]
  {:records (reduce + (vals (select-keys m [:fp :fn :tp :tn])))
   :fp-count (:fp m)
   :tpr (tp-rate m)
   :fpr (fp-rate m)
   :accuracy (accuracy m)
   :precision (precision m)
   :f1 (f1-score m)
   ; Cost metric to try to tease out how much more risky a false positive is.
   :cost (+ (fn-rate m) (* 1000 (fp-rate m)))})

(defn print-acc [acc]
  (let [{records :records tpr :tpr fp-count :fp-count fpr :fpr accuracy :accuracy precision :precision f1 :f1 cost :cost} acc
        fmt (fn [v] (format "%.6f" (double v)))]
    (str records " " fp-count " " (s/join " " (map fmt [tpr fpr accuracy precision f1 cost])))))

;; NOTE: This classification is inverted from standard analysis.
(defn classify [& args]
  (case args
    ["0" "0"] :tp
    ["0" "1"] :fp
    ["1" "1"] :tn
    ["1" "0"] :fn))

(defn parse-line [line]
  (let [[id res pred fl] (s/split line #",")]
    [id (classify pred res) (Double/parseDouble fl)]))

(defn line-fn
  "Derive function that parses a line for ROC calculations, based on provided threshold.
  Output is a vector of model ID, whether results is positive, and the predicted value."
  [threshold]
  (fn [line]
    (let [[id res _ fl] (s/split line #",")
          pred (Double/parseDouble fl)
          cls (if (= res "1")
                (>= pred threshold)
                (< pred threshold))]
      [id cls (- 1.0 pred)])))

(defn line-for-auc [line]
  (let [[id res pred fl] (s/split line #",")
        cls (classify pred res)]
    [id (or (= :tp cls) (= :tn cls)) (- 1.0 (Double/parseDouble fl))]))

(def fresh {:tp 0 :fp 0 :tn 0 :fn 0 :loss 0.0 :rev 0.0})

(defn add-line [acc line]
  (let [[id pred price] (parse-line line)
        loss (if (= :fp pred) price 0.0)
        gain (if (= :tn pred) price 0.0)]
    (update-in
      (update-in
        (if (contains? acc id)
          (update-in acc [id pred] inc)
          (update-in (assoc acc id fresh) [id pred] inc))
        [id :loss] + loss)
      [id :rev] + gain)))

(defn quad [m]
  (let [total (reduce + (vals (select-keys m [:fp :fn :tp :tn])))
        cost (/ (+ (:fn m) (* 1000 (:fp m))) total)
        loss (:loss m)
        cpm (+ loss (:rev m))
        pct-loss (if (zero? cpm) 0.0 (/ loss cpm))
        fltr (/ (:tp m) total)
        lev (if (zero? pct-loss) 0.0 (/ fltr pct-loss))]
    [(/ (:fp m) total) (/ (:fn m) total) cost pct-loss fltr lev]))

(defn print-summary [m]
  (let [[f-p f-n cost pct-loss fltr lev] m
        fmt (fn [v] (format "%.6f" (double v)))]
    (s/join " " (map fmt [f-p f-n cost pct-loss fltr lev]))))

(defn worse [acc e]
  (map (partial apply max)
       (map vector acc (quad e))))

(defn hi-lo [coll]
  (let [t (map w-acc coll)]
    [(reduce (fn [acc e] (map max acc e)) [0 0 0] t)
     (reduce (fn [acc e] (map min acc e)) [1 1 1] t)]))

(defn adequate? [[k v]]
  (< 4000
     (reduce + (vals (select-keys v [:fp :fn :tp :tn])))))

(defn per-id-auc [filename threshold]
  (with-open [r (io/reader filename)]
    (let [lfn (line-fn threshold)]
      (doseq [line (line-seq r)]

        )
      ;; TODO group-by but also enforce that data sets are large enough.
          )))

(defn run-auc [filename threshold]
  (with-open [r (io/reader filename)]
    (let [lfn (line-fn threshold)]
      (auc (map lfn (line-seq r))))))

(defn analyze [filename]
  (with-open [r (io/reader filename)]
    (let [analysis (reduce add-line {} (line-seq r))
          large-enough (filter adequate? analysis)
          overall (apply (partial merge-with +) (vals large-enough))]
      (println "id records fp-count recall fallout accuracy precision f1-score weighted-cost")
      (println
        (str "all " (print-acc (w-acc overall))))
      (doseq [[id stats] large-enough]
        (println (str id " " (print-acc (w-acc stats))))))))

(defn old-analyze [filename]
  (with-open [r (io/reader filename)]
    (let [analysis (reduce add-line {} (line-seq r))
          large-enough (filter adequate? analysis)
          overall (apply (partial merge-with +) (vals large-enough))
          worst (reduce worse [0 0 0 0 0 0] (vals large-enough))]
      (println
        (str (print-summary (quad overall))
             " "
             (print-summary worst))))))

(defn map-file [filename]
  (with-open [rdr (io/reader filename)]
    (doall (map parse-line (line-seq rdr)))))

(defn summarize [m]
  (let [total (reduce + (vals m))
        f-neg (get m :fn 0)
        f-pos (get m :fp 0)]
    {:fp-rate (double (/ f-pos total))
     :fn-rate (double (/ f-neg total))
     :cost (double (/ (+ f-neg (* 1000 f-pos)) total))
     :cost-100 (double (/ (+ f-neg (* 100 f-pos)) total))}))

(defn overall [res]
  (summarize (frequencies (map second res))))

(defn per-id [res]
  (reduce-kv
    #(assoc %1 %2 (summarize (frequencies (map second %3))))
    {}
    (group-by first res)))

(def fmt-dbl (partial format "%.6f "))

;; Utility function for pulling in data at the REPL.
(defn get-lines [fname]
  (with-open [r (io/reader fname)]
    (doall (line-seq r))))
