(ns euler-062.core-test
  (:require [clojure.test :refer :all]
            [euler-062.core :refer :all]))

(deftest lowest-by-digits-test
  (testing "1 returns 1."
    (is (= 1.0 (lowest-number-by-digits 1))))
  (testing "Provided 9, has 9 digits."
    (is (= 9 (digits-in (long (lowest-number-by-digits 9))))))
  (testing "Subtract 1 from returned value and it has one less digit."
    (is (= 14 (digits-in (dec (long (lowest-number-by-digits 15))))))))

(deftest next-cube-test
  (testing "Integer with integer cube root returns cube root."
    (is (= 10 (next-cube (cube 10)))))
  (testing "Returns next highest number if cube root is not an integer."
    (is (= 11 (next-cube (inc (cube 10)))))))

(deftest digits-in-test
  (testing "digits-in returns expected value."
    (is (= 5 (digits-in 54321)))))

(deftest cubes-test
  (testing "First several cubes match."
    (is (= '(1 8 27 64 125) (take 5 (cubes 1)))))
  (testing "345th cube is value expected (known from problem statement)."
    (is (= 41063625 (nth (cubes 1) (dec 345)))))
  (testing "384th cube is value expected (known from problem statement)."
    (is (= 56623104 (nth (cubes 1) (dec 384)))))
  (testing "405th cube is value expected (known from problem statement)."
    (is (= 66430125 (nth (cubes 1) (dec 405))))))

(deftest cubes-by-digits-test
  (testing "All values returned have the expected number of digits."
    (is (every? #(= 5 (digits-in %)) (cubes-by-digits 5))))
  (testing "All 2 digits cubes."
    (is (= '(27 64) (cubes-by-digits 2)))))

(deftest find-candidates-test
  (testing "Find know candidates for 2 digit values."
    (is (= '(125 512) (find-candidates 2 (cubes-by-digits 3)))))
  (testing "Find know candidates for 3 digit values."
    (is (= '(41063625 56623104 66430125) (find-candidates 3 (cubes-by-digits 8)))))
  (testing "When there are no candidates."
    (is (= '() (find-candidates 5 (cubes-by-digits 11))))))

(deftest euler-062-test
  (testing "Smallest value with 2 permutations."
    (is (= 125 (euler-062 2))))
  (testing "Smallest value with 3 permutations (from example text)."
    (is (= 41063625 (euler-062 3))))
  (testing "Smallest value with 4 permutations."
    (is (= 1006012008 (euler-062 4))))
  (testing "Smallest value with 5 permutations."
    (is (= 127035954683 (euler-062 5)))))
