(ns aoc.day-13-test
  "Tests against the aoc.day-13 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-13 :as day-13]))

(def input (string/split-lines (slurp "resources/day-13-input.txt")))

(deftest next-bus-test
  (testing "ID of the next bus given the current time."
    (is (= [59 944] (day-13/next-bus "7,13,x,x,59,x,31,19" 939)))
    (let [start (Integer/parseInt (first input))
          sched (last input)]
      (is (= [19 1000426] (day-13/next-bus sched start))))))

(deftest bus-wait-product-test
  (testing "product of next bus ID and time to wait."
    (is (= 295
           (let [start 939
                 [bus ts] (day-13/next-bus "7,13,x,x,59,x,31,19" start)]
             (* bus (- ts start)))))
    (let [start (Integer/parseInt (first input))
          sched (last input)
          [bus ts] (day-13/next-bus sched start)]
      (is (= 171 (* bus (- ts start)))))))

(deftest find-schedule-test
  (testing "find starting timestamp of scheduling sequence."
    (is (= 3417 (day-13/find-schedule "17,x,13,19")))
    ; (is (= 754018 (day-13/find-schedule "67,7,59,61")))
    ; (is (= 779210 (day-13/find-schedule "67,x,7,59,61")))
    ; (is (= 1068781 (day-13/find-schedule "7,13,x,x,59,x,31,19")))
    ; (is (= 1261476 (day-13/find-schedule "67,7,x,59,61")))
    ; (is (= 1202161486 (day-13/find-schedule "1789,37,47,1889")))
    ; (is (= 3417 (day-13/find-schedule (last input))))
    ))
