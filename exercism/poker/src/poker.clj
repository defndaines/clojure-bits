(ns poker)

(def ranks
  "Rank mapping of face value to a character that can be used to sort
  alphabetically."
  {"A" \0
   "K" \1
   "Q" \2
   "J" \3
   "10" \4
   "9" \5
   "8" \6
   "7" \7
   "6" \8
   "5" \9
   "4" \:
   "3" \;
   "2" \<})

(def low-ace-rank \=)

(defn ace-low
  "Change the rank of any Ace cards to treat it as the low card."
  [cards]
  (map
    (fn [card]
      (if (= "A" (:face card))
        (assoc card :rank low-ace-rank)
        card))
    cards))

(defn split-cards
  "Parse and break apart cards into sortable values."
  [hand]
  (map
    (fn [card]
      (let [[[_ f s]] (re-seq #"(\w+)([SHCD])" card)]
        {:face f
         :rank (ranks f)
         :suit s
         :hand hand}))
    (clojure.string/split hand #" ")))

(defn report
  "Create a tuple with a sortable rank value followed by the original hand."
  [cards hand-rank]
  [(clojure.string/join (conj (map :rank cards) hand-rank))
   (:hand (first cards))])

(defn sort-by-freq
  "Sort grouped cards by their frequency then rank. Most frequent cards are at
  the end, and when sets of cards have the same frequency, sort in ascending
  rank order."
  [grouped-cards]
  (sort-by (comp count second) grouped-cards))

(defn straight-ace-high
  "If the hand is a straight with Ace high, return the sorted cards."
  [cards]
  (let [sorted (sort-by :rank cards)]
    (when (apply = (map +
                        (map #(int (:rank %)) sorted)
                        (range 4 -1 -1)))
      sorted)))

(defn straight-ace-low
  "If the hand is a straight with Ace low, return the sorted cards."
  [cards]
  (let [sorted (sort-by :rank (ace-low cards))]
    (when (apply = (map +
                        (map #(int (:rank %)) sorted)
                        (range 4 -1 -1)))
      sorted)))

(defn straight-flush? [cards]
  (when (= 1 (count (set (map :suit cards))))
    (cond
      (straight-ace-high cards) (report (sort-by :rank cards) 0)
      (straight-ace-low cards) (report (sort-by :rank (ace-low cards)) 0))))

(defn four-of-a-kind? [cards]
  (let [g (group-by :face cards)]
    (when (= 2 (count g))
      (let [[[_ [high-card]] [_ quad]] (sort-by-freq g)]
        (when (= 4 (count quad))
          (report (conj quad high-card) 1))))))

(defn full-house? [cards]
  (let [g (group-by :face cards)]
    (when (= 2 (count g))
      (let [[[_ pair] [_ tripel]] (sort-by-freq g)]
        (when (and (= 2 (count pair))
                   (= 3 (count tripel)))
          (report (concat tripel pair) 2))))))

(defn flush? [cards]
  (when (= 1 (count (set (map :suit cards))))
    (report (sort-by :rank cards) 3)))

(defn straight? [cards]
  (cond
    (straight-ace-high cards) (report (sort-by :rank cards) 4)
    (straight-ace-low cards) (report (sort-by :rank (ace-low cards)) 4)))

(defn three-of-a-kind? [cards]
  (let [g (group-by :face cards)]
    (when (= 3 (count g))
      (let [[[_ [a]] [_ [b]] [_ tripel]] (sort-by-freq g)]
        (when (= 3 (count tripel))
          (report (concat tripel (sort-by :rank [a b])) 5))))))

(defn two-pairs? [cards]
  (let [g (group-by :face cards)]
    (when (= 3 (count g))
      (let [[[_ high-card] [_ first-pair] [_ second-pair]]
            (sort-by-freq g)]
        (when (and (= 2 (count first-pair))
                   (= 2 (count second-pair)))
          (report (concat second-pair first-pair high-card) 6))))))

(defn one-pair? [cards]
  (let [g (group-by :face cards)]
    (when (= 4 (count g))
      (let [[[_ [a]] [_ [b]] [_ [c]] [_ pair]]
            (sort-by-freq g)]
        (report (conj pair c b a) 7)))))

(defn high-card [cards]
  (report (sort-by :rank cards) 8))


(defn rank-hand [hand]
  (let [cards (split-cards hand)]
    (some identity
          (map
            #(% cards)
            [straight-flush?
             four-of-a-kind?
             full-house?
             flush?
             straight?
             three-of-a-kind?
             two-pairs?
             one-pair?
             high-card]))))


(defn best-hands [hands]
  (let [ranked (sort-by first (map rank-hand hands))
        best-rank (ffirst ranked)]
    (keep
      #(when (= best-rank (first %)) (second %))
      ranked)))
