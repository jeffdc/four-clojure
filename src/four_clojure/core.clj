(ns four-clojure.core
  (:gen-class))

(defn -main
  [& args]

; http://www.4clojure.com/problem/23
; Write a funciton which reverses a sequence (no rseq, reverse)
  (defn rev [xs]
      (into '() xs)
    )

  (assert (= (rev [1 2 3 4 5]) [5 4 3 2 1]))
  (assert (= (rev (sorted-set 5 7 2 7)) '(7 5 2)))
  (assert (= (rev [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]]))

; http://www.4clojure.com/problem/24
; Write a function which returns the sum of a sequence of numbers
  (defn sum [xs] (reduce + xs))

  (assert (= (sum [1 2 3]) 6))
  (assert (= (sum (list 0 -2 5 5)) 8))
  (assert (= (sum #{4 2 1}) 7))
  (assert (= (sum '(0 0 -1)) -1))
  (assert (= (sum '(1 10 3)) 14))

; http://www.4clojure.com/problem/25
  (defn odds [xs] (filter odd? xs))

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
  (defn palindrome? [xs]
    (= (seq xs) (reverse xs))
  )

  (assert (false? (palindrome? '(1 2 3 4 5))))
  (assert (true?  (palindrome? "racecar")))
  (assert (true?  (palindrome? [:foo :bar :foo])))
  (assert (true?  (palindrome? '(1 1 3 3 1 1))))
  (assert (false? (palindrome? '(:a :b :c))))

; http://www.4clojure.com/problem/28
; Flatten a Sequence (no flatten allowed)
  (defn pancake [xs]
    (if (empty? xs)
      xs
      (if (sequential? (first xs))
        (concat (pancake (first xs))(pancake (rest xs)))
        (cons (first xs) (pancake (rest xs)) ))))

  (assert (= (pancake '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)))
  (assert (= (pancake ["a" ["b"] "c"]) '("a" "b" "c")))
  (assert (= (pancake '((((:a))))) '(:a)))

; http://www.4clojure.com/problem/29
; Get the Caps
; Write a function which takes a string and returns a new string containing only the capital letters
  (defn caps [xs]
    (apply str (re-seq #"[A-Z]" xs)))

  (assert (= (caps "HeLlO, WoRlD!") "HLOWRD"))
  (assert (empty? (caps "nothing")))
  (assert (= (caps "$#A(*&987Zf") "AZ"))

; http://www.4clojure.com/problem/30
; Write a function which removes consecutive duplicates from a sequence
  (defn compress [xs]
    (map first (partition-by identity xs)))

  (assert (= (apply str (compress "Leeeeeerrroyyy")) "Leroy"))
  (assert (= (compress [1 1 2 3 3 2 2 3]) '(1 2 3 2 3)))
  (assert (= (compress [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))

; http://www.4clojure.com/problem/31
; Write a function which packs consecutive duplicates into sub-lists.
  (defn pack [xs]
    (partition-by identity xs))

  (assert (= (pack [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3))))
  (assert (= (pack [:a :a :b :b :c]) '((:a :a) (:b :b) (:c))))
  (assert (= (pack [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4]))))

; http://www.4clojure.com/problem/32
; Write a function which duplicates each element of a sequence.
  (defn breeder [xs]
    (interleave xs xs))

  (assert (= (breeder [1 2 3]) '(1 1 2 2 3 3)))
  (assert (= (breeder [:a :a :b :b]) '(:a :a :a :a :b :b :b :b)))
  (assert (= (breeder [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))
  (assert (= (breeder [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))

; http://www.4clojure.com/problem/33
; Write a function which replicates each element of a sequence a variable number of times.
  (defn zeroxer [xs x]
    (mapcat #(repeat x %) xs))

  (assert (= (zeroxer [1 2 3] 2) '(1 1 2 2 3 3)))
  (assert (= (zeroxer [:a :b] 4) '(:a :a :a :a :b :b :b :b)))
  (assert (= (zeroxer [4 5 6] 1) '(4 5 6)))
  (assert (= (zeroxer [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4])))
  (assert (= (zeroxer [44 33] 2) [44 44 33 33]))


; http://www.4clojure.com/problem/34
; Write a function which creates a list of all integers in a given range. (No range)
  (defn ranger-rick-hickey [b e]
    (take (- e b) (iterate inc b)))

  (assert (= (ranger-rick-hickey 1 4) '(1 2 3)))
  (assert (= (ranger-rick-hickey -2 2) '(-2 -1 0 1)))
  (assert (= (ranger-rick-hickey 5 8) '(5 6 7)))

; http://www.4clojure.com/problem/38
; Write a function which takes a variable number of parameters and returns the maximum value. (No max or max-key)
  (defn maximilian [& xs]
    (reduce #(if (> % %2) % %2) xs))

  (assert (= (maximilian 1 8 3 4) 8))
  (assert (= (maximilian 30 20) 30))
  (assert (= (maximilian 45 67 11) 67))

; http://www.4clojure.com/problem/39
; Write a function which takes two sequences and returns the first item from each, then the second item
; from each, then the third, etc. (no interleave)
  (defn complect [xs ys]
    (mapcat vector xs ys))

  (assert (= (complect [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c)))
  (assert (= (complect [1 2] [3 4 5 6]) '(1 3 2 4)))
  (assert (= (complect [1 2 3 4] [5]) [1 5]))
  (assert (= (complect [30 20] [25 15]) [30 25 20 15]))

; http://www.4clojure.com/problem/40
; Write a function which separates the items of a sequence by an arbitrary value. (no interpose)
  (defn conjoin [j xs]
    (butlast (mapcat #(vector % j) xs)))

  (assert (= (conjoin 0 [1 2 3]) [1 0 2 0 3]))
  (assert (= (apply str (conjoin ", " ["one" "two" "three"])) "one, two, three"))
  (assert (= (conjoin :z [:a :b :c :d]) [:a :z :b :z :c :z :d]))

(println "Yee-haw it all worked!")
)
