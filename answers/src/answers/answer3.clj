(ns answers.answer3)

(defn trial-divisors-gen [x y]
  (lazy-seq (let [step (case (- y x) 2 4 4 2)]
    (cons x (trial-divisors-gen y (+ y step))))))

(def trial-divisors-1
  (lazy-seq (cons 2 (cons 3 (trial-divisors-gen 5 7)))))

(defn quotient-and-reminder [n dk]
  [(bigint (/ n dk)) (rem n dk)])

(declare prime-factor-iter)

(defn prime-factor-iter-helper [k n divisors acc]
  (let [dk (nth divisors k)
        [q r] (quotient-and-reminder n dk)]
    (if (zero? r)
      #(prime-factor-iter k q divisors (conj acc dk))
      (if (> q dk)
        #(prime-factor-iter-helper (inc k) n divisors acc)
        (conj acc n)))))

(defn prime-factor-iter [k n divisors acc]
  (if (= n 1)
    acc
    #(prime-factor-iter-helper k n divisors acc)))

(defn prime-factors [num]
  (trampoline prime-factor-iter 0 num trial-divisors-1 []))

(def result (apply max (prime-factors 600851475143N)))