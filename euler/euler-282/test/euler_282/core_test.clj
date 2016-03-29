(ns euler-282.core-test
  (:require [clojure.test :refer :all]
            [euler-282.core :refer :all]))

;;A(1, 0) = 2, A(2, 2) = 7 and A(3, 4) = 125
(deftest ackermann-test
  (testing "A(1, 0) = 2"
    (is (= 2 (acker 1 0))))
  (testing "A(2, 2) = 7"
    (is (= 7 (acker 2 2))))
  (testing "A(3, 4) = 125"
    (is (= 125 (acker 3 4)))))
