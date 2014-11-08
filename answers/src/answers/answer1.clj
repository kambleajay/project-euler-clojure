(ns answers.answer1
  (:gen-class))

(defn divisible-by-fn [denom]
  (fn [x] (zero? (rem x denom))))

(def divisible-by-3? (divisible-by-fn 3))
(def divisible-by-5? (divisible-by-fn 5))
(defn divisible-by-3-or-5? [x]
  (or
    (divisible-by-3? x)
    (divisible-by-5? x)))

(defn result [& args]
  (println (apply + (filter divisible-by-3-or-5? (range 0 1000)))))