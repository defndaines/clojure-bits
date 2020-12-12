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

(def part-two-round-two
  ["#.LL.LL.L#"
   "#LLLLLL.LL"
   "L.L.L..L.."
   "LLLL.LL.LL"
   "L.LL.LL.LL"
   "L.LLLLL.LL"
   "..L.L....."
   "LLLLLLLLL#"
   "#.LLLLLL.L"
   "#.LLLLL.L#"])

(def part-two-round-three
  ["#.L#.##.L#"
   "#L#####.LL"
   "L.#.#..#.."
   "##L#.##.##"
   "#.##.#L.##"
   "#.#####.#L"
   "..#.#....."
   "LLL####LL#"
   "#.L#####.L"
   "#.L####.L#"])

(def part-two-round-four
  ["#.L#.L#.L#"
   "#LLLLLL.LL"
   "L.L.L..#.."
   "##LL.LL.L#"
   "L.LL.LL.L#"
   "#.LLLLL.LL"
   "..L.L....."
   "LLLLLLLLL#"
   "#.LLLLL#.L"
   "#.L#LL#.L#"])

(def part-two-round-five
  ["#.L#.L#.L#"
   "#LLLLLL.LL"
   "L.L.L..#.."
   "##L#.#L.L#"
   "L.L#.#L.L#"
   "#.L####.LL"
   "..#.#....."
   "LLL###LLL#"
   "#.LLLLL#.L"
   "#.L#LL#.L#"])

(def part-two-round-six
  ["#.L#.L#.L#"
   "#LLLLLL.LL"
   "L.L.L..#.."
   "##L#.#L.L#"
   "L.L#.LL.L#"
   "#.LLLL#.LL"
   "..#.L....."
   "LLL###LLL#"
   "#.LLLLL#.L"
   "#.L#LL#.L#"])

(def input (string/split-lines (slurp "resources/day-11-input.txt")))

(deftest round-test
  (testing "applying logic for a round of seating reassignment."
    (is (= after-first-round (day-11/round initial-seat-layout day-11/adjacent 4)))
    (is (= after-second-round (day-11/round after-first-round day-11/adjacent 4)))
    (is (= after-third-round (day-11/round after-second-round day-11/adjacent 4)))
    (is (= after-fourth-round (day-11/round after-third-round day-11/adjacent 4)))
    (is (= after-fifth-round (day-11/round after-fourth-round day-11/adjacent 4)))
    (is (= after-fifth-round (day-11/round after-fifth-round day-11/adjacent 4)))))

(deftest stabilize-test
  (testing "run until stabilized."
    (is (= after-fifth-round (day-11/stabilize initial-seat-layout day-11/adjacent 4)))))

(deftest occupied-test
  (testing "how many seats are occupied."
    (is (= 37 (day-11/occupied after-fifth-round)))
    #_(is (= 2412 (day-11/occupied (day-11/stabilize input day-11/adjacent 4))))
    (is (= 26 (day-11/occupied part-two-round-six)))
    #_(is (= 2176 (day-11/occupied (day-11/stabilize input day-11/in-view 5))))))

(deftest in-view-test
  (testing "when seats exist in all directions."
    (is (= [\# \# \# \# \# \# \# \#]
           (day-11/in-view [".......#."
                            "...#....."
                            ".#......."
                            "........."
                            "..#L....#"
                            "....#...."
                            "........."
                            "#........"
                            "...#....."] [4 3]))))
  (testing "when only one seat is in view."
      (is (= [\L]
           (day-11/in-view ["............."
                            ".L.L.#.#.#.#."
                            "............."] [1 1]))))
  (testing "when no seats are in view."
    (is (= []
           (day-11/in-view [".##.##."
                            "#.#.#.#"
                            "##...##"
                            "...L..."
                            "##...##"
                            "#.#.#.#"
                            ".##.##."] [3 3])))))

(deftest round-with-in-view-test
  (testing "stepping through rounds using the in-view neighbor function."
    (is (= after-first-round (day-11/round initial-seat-layout day-11/in-view 5)))
    (is (= part-two-round-two (day-11/round after-first-round day-11/in-view 5)))
    (is (= part-two-round-three (day-11/round part-two-round-two day-11/in-view 5)))
    (is (= part-two-round-four (day-11/round part-two-round-three day-11/in-view 5)))
    (is (= part-two-round-five (day-11/round part-two-round-four day-11/in-view 5)))
    (is (= part-two-round-six (day-11/round part-two-round-five day-11/in-view 5)))
    (is (= part-two-round-six (day-11/round part-two-round-six day-11/in-view 5)))))
