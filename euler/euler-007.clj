;By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
;What is the 10001st prime number?

;StackOverflowError
(loop
 [found 0 number 2]
 (if (prime? number)
     (if (= 10001 (inc found)) number
         (recur (inc found) (inc number)))
     (recur found (inc number))))

; StackOverflowError after 800th somewhere
(defn nth-prime
 [goal]
 (loop [x 2 found 0] 
  (let [is-prime (prime? x)]
   (if (and is-prime (= goal found))
        x
        (recur (inc x) (if is-prime (inc found) found))))))

; Still not solved ....

(defn next-prime
 [x]
 (let [y (inc x)]
 (if (prime? y) y (next-prime y))))

; Still gets a StackOverflow around 1900
(defn nth-prime
 ([goal] (nth-prime goal 2 0))
 ([goal cur found]
 (if (= goal (inc found)) cur
     (recur goal (next-prime cur) (inc found)))))

; Answer: 104743
; "Elapsed time: 30827.957 msecs"
