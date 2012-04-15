(def untrusted (with-meta {:command "clean-table" :subject "users"}
                {:safe false :io true}))

(meta untrusted)

(def still-suspect (assoc untrusted :complete? false))

; Carries with the object, though doesn't affect equality.
(meta still-suspect)

(defn testing-meta
 "testing metadata for functions"
 {:safe true :console true}
 []
 (println "Hello from meta!"))

(meta (var testing-meta))
