(ns answers.answer2)

(defn fib [a b] (cons a (lazy-seq (fib b (+ b a)))))

(def fib-nums (fib 1 2))

(def less-than-four-million (take-while #(< % 4000000) fib-nums))

(def result (apply + (filter even? less-than-four-million)))