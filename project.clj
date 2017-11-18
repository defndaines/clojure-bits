(defproject clojure-bits "0.1.0-SNAPSHOT"
  :description "Clojure code that doesn't belong to any particular project,
               particularly code used to work through problems and quizzes."
  :url "https://github.com/defndaines/clojure-bits"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC1"]
                 [org.clojure/data.generators "0.1.2"]]
  :profiles {:dev
             {:dependencies [[org.clojure/test.check "0.9.0"]]}})
