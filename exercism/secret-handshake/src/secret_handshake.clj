(ns secret-handshake)

(def codes
  ["wink"
   "double blink"
   "close your eyes"
   "jump"])

(defn commands [n]
  (let [actions (keep
                  #(when (bit-test n %) (get codes %))
                  (range 4))]
    (if (bit-test n 4)
      (reverse actions)
      actions)))
