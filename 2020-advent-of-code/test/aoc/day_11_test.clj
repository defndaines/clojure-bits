(ns aoc.day-11-test
  "Tests against the aoc.day-11 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-11 :as day-11]))

(def initial-seat-layout
  ["L.LL.LL.LL"
   "LLLLLLL.LL"
   "L.L.L..L.."
   "LLLL.LL.LL"
   "L.LL.LL.LL"
   "L.LLLLL.LL"
   "..L.L....."
   "LLLLLLLLLL"
   "L.LLLLLL.L"
   "L.LLLLL.LL"])

(def after-first-round
  ["#.##.##.##"
   "#######.##"
   "#.#.#..#.."
   "####.##.##"
   "#.##.##.##"
   "#.#####.##"
   "..#.#....."
   "##########"
   "#.######.#"
   "#.#####.##"])

(def after-second-round
  ["#.LL.L#.##"
   "#LLLLLL.L#"
   "L.L.L..L.."
   "#LLL.LL.L#"
   "#.LL.LL.LL"
   "#.LLLL#.##"
   "..L.L....."
   "#LLLLLLLL#"
   "#.LLLLLL.L"
   "#.#LLLL.##"])

(def after-third-round
  ["#.##.L#.##"
   "#L###LL.L#"
   "L.#.#..#.."
   "#L##.##.L#"
   "#.##.LL.LL"
   "#.###L#.##"
   "..#.#....."
   "#L######L#"
   "#.LL###L.L"
   "#.#L###.##"])

(def after-fourth-round
  ["#.#L.L#.##"
   "#LLL#LL.L#"
   "L.L.L..#.."
   "#LLL.##.L#"
   "#.LL.LL.LL"
   "#.LL#L#.##"
   "..L.L....."
   "#L#LLLL#L#"
   "#.LLLLLL.L"
   "#.#L#L#.##"])

(def after-fifth-round
  ["#.#L.L#.##"
   "#LLL#LL.L#"
   "L.#.L..#.."
   "#L##.##.L#"
   "#.#L.LL.LL"
   "#.#L#L#.##"
   "..L.L....."
   "#L#L##L#L#"
   "#.LLLLLL.L"
   "#.#L#L#.##"])

(def input (string/split-lines (slurp "resources/day-11-input.txt")))

(deftest round-test
  (testing "applying logic for a round of seating reassignment."
    (is (= after-first-round (day-11/round initial-seat-layout)))
    (is (= after-second-round (day-11/round after-first-round)))
    (is (= after-third-round (day-11/round after-second-round)))
    (is (= after-fourth-round (day-11/round after-third-round)))
    (is (= after-fifth-round (day-11/round after-fourth-round)))
    (is (= after-fifth-round (day-11/round after-fifth-round)))))

(deftest stabilize-test
  (testing "run until stabilied."
    (is (= after-fifth-round (day-11/stabilize initial-seat-layout)))))

(deftest occupied-test
  (testing "how many seats are occupied."
    (is (= 37 (day-11/occupied after-fifth-round)))
    (is (= 2412 (day-11/occupied (day-11/stabilize input))))))
