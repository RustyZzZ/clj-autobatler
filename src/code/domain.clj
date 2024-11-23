(ns code.domain
  (:require
   [clojure.math :as math]
   [code.dice :refer [dice-amount-coef]]
   [code.config :refer [coef-by-race stat-config calculate-stats]]))

(defrecord Attributes [strength dexterity])
(defrecord Stats [health damage evasion accuracy])
(defrecord Minion [name attributes coefficients stats])

(defn create-attributes [strength dexterity]
  {:pre [(pos-int? strength)
         (pos-int? dexterity)]}
  (->Attributes strength dexterity))

(defn create-coefficients [race]
  {:pre [(contains? coef-by-race race)]}
  (race coef-by-race))

(defn create-minion [name attributes coefficients]
  (->Minion name attributes coefficients (apply ->Stats
                                                (calculate-stats attributes coefficients stat-config))))

(comment
  (calculate-stats (->Attributes 10 10) (:human coef-by-race) stat-config))

(defn get-stat [stat minion]
  (stat (:stats minion)))

(defn dice-amount [stat]
  (max 1 (math/floor-div stat dice-amount-coef)))

(defn hit-dice-amount [minion]
  (dice-amount (get-stat :accuracy minion)))

(defn evasion-dice-amount [minion]
  (dice-amount (get-stat :evasion minion)))

(defn damage-dice-amount [minion]
  (dice-amount (get-stat :damage minion)))


