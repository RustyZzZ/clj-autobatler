(ns code.dice)

(def d20 20)
(def d6 6)
; (def d4 4)
; (def d8 8)
; (def d12 12)
; (def d10 10)
; (def d100 100)

(def dice-amount-coef 10)
(defn throw-dice
  ([size] (+ (rand-int size) 1))
  ([amount size] (reduce + (take amount (repeatedly #(throw-dice size))))))


