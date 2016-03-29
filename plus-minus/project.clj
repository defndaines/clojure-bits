(defproject plus-minus "0.1.0-SNAPSHOT"
  :description "Over- and under-charge totals per store."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.3"]
                 [clojurewerkz/money "1.9.0"]]
  :main ^:skip-aot plus-minus.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
