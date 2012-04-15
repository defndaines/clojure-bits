(defn print-amounts [[amount-1 amount-2]]
 (println "amounts are:" amount-1 "and" amount-2))

(defn print-amounts-multiple [[amount-1 amount-2 & remaining]]
 (println "Amounts are:" amount-1 "," amount-2 "and" remaining))

;; :as all captures the entire arg list (vectors, maps, whatever)
(defn print-all-amounts [[amount-1 amount-2 & remaining :as all]]
 (println "Amounts are:" amount-1 "," amount-2 "and" remaining)
 (println "Also, all the amounts are:" all))

;; Vector of vectors
(defn print-first-category [[[category amount] & _]]
 (println "First category was:" category)
 (println "First amount was:" amount))

(def expenses [[:books 49.95] [:coffee 4.95] [:caltrain 2.25]])

(defn describe-salary [{first :first-name last :last-name annual :salary}]
 (println first last "earns" annual))

; Map destructuring with default value (:or)
(defn describe-salary-3 [{first :first-name last :last-name annual :salary bonus :bonus-percentage :or {bonus 5}}]
 (println first last "earns" annual "with" bonus "percent bonus"))

(defn greet-user [{:keys [first-name last-name]}]
 (println "Welcome," first-name last-name))
