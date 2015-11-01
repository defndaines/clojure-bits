(ns euler-061.core-test
  (:require [clojure.test :refer :all]
            [euler-061.core :refer :all]))

(deftest triangle-numbers
  (testing "Triangle numbers"
    ;; Triangle | P3,n=n(n+1)/2 | 1, 3, 6, 10, 15, ...
    (is (= '(1 3 6 10 15) (take 5 triangles)))))

(deftest square-numbers
  (testing "Square numbers"
    ;; Square | P4,n=n2 | 1, 4, 9, 16, 25, ...
    (is (= '(1 4 9 16 25) (take 5 squares)))))

(deftest pentagonal-numbers
  (testing "Pentagonal numbers"
    ;; Pentagonal | P5,n=n(3n−1)/2 | 1, 5, 12, 22, 35, ...
    (is (= '(1 5 12 22 35) (take 5 pentagonals)))))

(deftest hexagonal-numbers
  (testing "Hexagonal numbers"
    ;; Hexagonal | P6,n=n(2n−1) | 1, 6, 15, 28, 45, ...
    (is (= '(1 6 15 28 45) (take 5 hexagonals)))))

(deftest heptagonal-numbers
  (testing "Heptagonal numbers"
    ;; Heptagonal | P7,n=n(5n−3)/2 | 1, 7, 18, 34, 55, ...
    (is (= '(1 7 18 34 55) (take 5 heptagonals)))))

(deftest octagonal-numbers
  (testing "Octagonal numbers"
    ;; Octagonal | P8,n=n(3n−2) | 1, 8, 21, 40, 65, ...
    (is (= '(1 8 21 40 65) (take 5 octagonals)))))
