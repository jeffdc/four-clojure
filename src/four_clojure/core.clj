(ns four-clojure.core
  (:gen-class))

(defn -main
  [& args]

; http://www.4clojure.com/problem/23
; Write a funciton which reverses a sequence (no rseq, reverse)
  (defn rev [s]
      (into '() s)
    )

  (assert (= (rev [1 2 3 4 5]) [5 4 3 2 1]))
  (assert (= (rev (sorted-set 5 7 2 7)) '(7 5 2)))
  (assert (= (rev [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]]))

; http://www.4clojure.com/problem/24
; Write a function which returns the sum of a sequence of numbers
  (defn sum [x] (reduce + x))

  (assert (= (sum [1 2 3]) 6))
  (assert (= (sum (list 0 -2 5 5)) 8))
  (assert (= (sum #{4 2 1}) 7))
  (assert (= (sum '(0 0 -1)) -1))
  (assert (= (sum '(1 10 3)) 14))

; http://www.4clojure.com/problem/25
  (defn odds [s] (filter odd? s))

  (assert (= (odds #{1 2 3 4 5}) '(1 3 5)))
  (assert (= (odds [4 2 1 6]) '(1)))
  (assert (= (odds [2 2 4 6]) '()))
  (assert (= (odds [1 1 1 3]) '(1 1 1 3)))

; http://www.4clojure.com/problem/26
; Write a function which returns the first X fibonacci numbers.
  (defn fib [x]
    (take x ((fn rfib [a b]
                 (lazy-seq (cons a (rfib b (+ a b)))))
               1 1)))

  (assert (= (fib 3) '(1 1 2)))
  (assert (= (fib 6) '(1 1 2 3 5 8)))
  (assert (= (fib 8) '(1 1 2 3 5 8 13 21)))

; http://www.4clojure.com/problem/27
; Write a function which returns true if the given sequence is a palindrome.
  (defn palindrome? [x]
    (= (seq x) (reverse x))
  )

  (assert (false? (palindrome? '(1 2 3 4 5))))
  (assert (true?  (palindrome? "racecar")))
  (assert (true?  (palindrome? [:foo :bar :foo])))
  (assert (true?  (palindrome? '(1 1 3 3 1 1))))
  (assert (false? (palindrome? '(:a :b :c))))

; http://www.4clojure.com/problem/28
; Flatten a Sequence (no flatten allowed)
  (defn pancake [x]
    (if (empty? x)
      x
      (if (sequential? (first x))
        (concat (pancake (first x))(pancake (rest x)))
        (cons (first x) (pancake (rest x)) ))))

  (assert (= (pancake '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)))
  (assert (= (pancake ["a" ["b"] "c"]) '("a" "b" "c")))
  (assert (= (pancake '((((:a))))) '(:a)))

; http://www.4clojure.com/problem/29
; Get the Caps
; Write a function which takes a string and returns a new string containing only the capital letters
  (defn caps [x]
    (apply str (re-seq #"[A-Z]" x)))

  (assert (= (caps "HeLlO, WoRlD!") "HLOWRD"))
  (assert (empty? (caps "nothing")))
  (assert (= (caps "$#A(*&987Zf") "AZ"))

; http://www.4clojure.com/problem/30
; Write a function which removes consecutive duplicates from a sequence
  (defn compress [x] (map first (partition-by identity x)))

  (= (apply str (compress "Leeeeeerrroyyy")) "Leroy")
  (= (compress [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
  (= (compress [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))
)
