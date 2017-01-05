(ns robot-name)

(def ^:private alpha (map char (range (int \A) (int (int \Z)))))

(def ^:private names (atom {}))

(defn ^:private r-name []
  (str (rand-nth alpha) (rand-nth alpha) (rand-int 10) (rand-int 10) (rand-int 10)))

(defn robot []
  (loop []
    (let [uid (r-name)]
      (if (not (contains? (clojure.set/map-invert @names) uid))
        (do (swap! names assoc uid uid)
            uid)
        (recur)))))

(defn robot-name [name]
  (get @names name))

(defn reset-name [name]
  (let [uid (robot)]
    (swap! names assoc name uid)
    uid))