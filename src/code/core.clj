(ns code.core
  (:gen-class)
  (:require
   [code.domain :refer [create-attributes create-coefficients create-minion]]
   [code.enums.race :as race]
   [code.mechanics :refer [fight]]))

(def hero-attributes (create-attributes 10 10))
(def hero-coef (create-coefficients race/human))
(def hero (create-minion "Rusty" hero-attributes hero-coef))
(def enemy-attributes (create-attributes 5 5))
(def enemy-coef (create-coefficients race/goblin))
(def enemy (create-minion "goblin" enemy-attributes enemy-coef))
(defn pretty-print [minion]
  (println (:name minion))
  (println "Health:" (:health (:stats minion)))
  (println "Damage:" (:damage (:stats minion)))
  (println "Evasion:" (:evasion (:stats minion)))
  (println "Accuracy:" (:accuracy (:stats minion))))

(defn -main
  []
  (println "Hero:")
  (pretty-print hero)
  (println "Enemy:")
  (pretty-print enemy)
  (fight hero enemy))




