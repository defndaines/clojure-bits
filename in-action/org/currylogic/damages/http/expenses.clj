(ns org.currylogic.damages.http.expenses)

; (use 'org.danlarkin.json)
(require '(clojure [xml :as xml-core]))

(defn import-transactions-xml-from-bank [url]
 (let [xml-document (xml-core/parse url)]
  ;; more code here
 ))

 ;(defn totals-by-day [start-date end-date]
 ; (let [expenses-by-day (load-tables start-date end-date)]
 ;  (encode-to-str expenses-by-day)))
