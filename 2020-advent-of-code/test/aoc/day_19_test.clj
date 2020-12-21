(ns aoc.day-19-test
  "Tests against the aoc.day-19 namespace."
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as string]
            [aoc.day-19 :as day-19]))

(def sample-rules
  ["0: 1 2"
   "1: \"a\""
   "2: 1 3 | 3 1"
   "3: \"b\""])

(def more-interesting
  ["0: 4 1 5"
   "1: 2 3 | 3 2"
   "2: 4 4 | 5 5"
   "3: 4 5 | 5 4"
   "4: \"a\""
   "5: \"b\""])

(def input (string/split-lines (slurp "resources/day-19-input.txt")))

(deftest build-rules-test
  (testing "building rules from a set."
    (is (= {"0" [:seq ["1" "2"]]
            "1" [:str "a"]
            "2" [:alt [["1" "3"] ["3" "1"]]]
            "3" [:str "b"]}
           (day-19/build-rules sample-rules)))))

(deftest regex-of-test
  (testing "getting a regex from an ID and set of rules."
    (is (= (.pattern #"^b$") (.pattern (day-19/regex-of sample-rules "3"))))
    (is (= (.pattern #"^a$") (.pattern (day-19/regex-of sample-rules "1"))))
    (is (= (.pattern #"^(ab|ba)$") (.pattern (day-19/regex-of sample-rules "2"))))
    (is (= (.pattern #"^a(ab|ba)$") (.pattern (day-19/regex-of sample-rules "0"))))))

(deftest match-test
  (testing "whether strings match the rules."
    (let [rule-0 (day-19/regex-of sample-rules "0")]
      (is (re-find rule-0 "aab"))
      (is (re-find rule-0 "aba"))))
  (testing "more interesting example."
    (let [rule-1 (day-19/regex-of more-interesting "1")]
      (is (re-find rule-1 "aaab"))
      (is (re-find rule-1 "aaba"))
      (is (re-find rule-1 "bbab"))
      (is (re-find rule-1 "bbba"))
      (is (re-find rule-1 "abaa"))
      (is (re-find rule-1 "abbb"))
      (is (re-find rule-1 "baaa"))
      (is (re-find rule-1 "babb")))
    (let [rule-0 (day-19/regex-of more-interesting "0")]
      (is (re-find rule-0 "aaaabb"))
      (is (re-find rule-0 "aaabab"))
      (is (re-find rule-0 "abbabb"))
      (is (re-find rule-0 "abbbab"))
      (is (re-find rule-0 "aabaab"))
      (is (re-find rule-0 "aabbbb"))
      (is (re-find rule-0 "abaaab"))
      (is (re-find rule-0 "ababbb"))
      (is (not (re-find rule-0 "bababa")))
      (is (not (re-find rule-0 "aaabbb")))
      (is (not (re-find rule-0 "aaaabbb"))))))

; Disabling, since changes to input will cause this to fail.
#_(deftest day-one-test
  (testing "count of matching messages."
    (let [rules (take-while #(not= "" %) input)
          rule-0 (day-19/regex-of rules "0")
          messages (rest (drop-while #(not= "" %) input))]
      (is (= 272 (count (filter #(re-find rule-0 %) messages)))))))

(def looping-rules
  ["0: 8 11"
   "1: \"a\""
   "2: 1 24 | 14 4"
   "3: 5 14 | 16 1"
   "4: 1 1"
   "5: 1 14 | 15 1"
   "6: 14 14 | 1 14"
   "7: 14 5 | 1 21"
   "8: 42 | 42 8"
   "9: 14 27 | 1 26"
   "10: 23 14 | 28 1"
   "11: 42 31 | 42 11 31"
   "12: 24 14 | 19 1"
   "13: 14 3 | 1 12"
   "14: \"b\""
   "15: 1 | 14"
   "16: 15 1 | 14 14"
   "17: 14 2 | 1 7"
   "18: 15 15"
   "19: 14 1 | 14 14"
   "20: 14 14 | 1 15"
   "21: 14 1 | 1 14"
   "22: 14 14"
   "23: 25 1 | 22 14"
   "24: 14 1"
   "25: 1 1 | 1 14"
   "26: 14 22 | 1 20"
   "27: 1 6 | 14 18"
   "28: 16 1"
   "31: 14 17 | 1 13"
   "42: 9 14 | 10 1"])

(def looping-messages
  ["abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa"
   "bbabbbbaabaabba"
   "babbbbaabbbbbabbbbbbaabaaabaaa"
   "aaabbbbbbaaaabaababaabababbabaaabbababababaaa"
   "bbbbbbbaaaabbbbaaabbabaaa"
   "bbbababbbbaaaaaaaabbababaaababaabab"
   "ababaaaaaabaaab"
   "ababaaaaabbbaba"
   "baabbaaaabbaaaababbaababb"
   "abbbbabbbbaaaababbbbbbaaaababb"
   "aaaaabbaabaaaaababaa"
   "aaaabbaaaabbaaa"
   "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa"
   "babaaabbbaaabaababbaabababaaab"
   "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"])

(deftest day-two-test
  (testing "sample looping data."
    (let [rule-0 (day-19/regex-of looping-rules "0")]
      (is (= 12
             (count (filter #(re-find rule-0 %) looping-messages))))))

  (testing "count of matching messages."
    (let [rules (take-while #(not= "" %) input)
          rule-0 (day-19/regex-of rules "0")
          messages (rest (drop-while #(not= "" %) input))]
      ; < 365 x 375 ; != 370
      (is (= 366 (count (filter #(re-find rule-0 %) messages)))))))
