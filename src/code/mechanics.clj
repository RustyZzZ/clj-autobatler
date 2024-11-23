(ns code.mechanics
  (:require
   [code.dice
    :as dice
    :refer [throw-dice]]
   [code.domain
    :refer [damage-dice-amount evasion-dice-amount hit-dice-amount]]))

(def stat_dices {:health   dice/d20
                 :damage   dice/d6
                 :evasion  dice/d20
                 :accuracy dice/d20})

(defn attack [attacker defender]
  (let [hit (throw-dice (hit-dice-amount attacker) (:accuracy stat_dices))
        evasion (throw-dice (evasion-dice-amount defender) (:evasion stat_dices))]
    (if (> hit evasion)
      (throw-dice (damage-dice-amount attacker) (:damage stat_dices))
      0)))

(defn update-health [minion dmg]
  (assoc-in minion [:stats :health] (- (:health (:stats minion)) dmg)))

(defn get-updated-hp [defender dmg]
  (let [hp-after (- (:health (:stats defender)) dmg)]
    (max 0 hp-after)))

(defn print-attack [attacker defender dmg]
  (let [attacker-name (:name attacker)
        defender-name (:name defender)
        defender-hp (get-updated-hp defender dmg)]
    (if (> dmg 0)
      (println attacker-name "punched" defender-name "for" dmg "dmg" defender-name "has" defender-hp "hp")
      (println attacker-name "missed to punch" defender-name "has" defender-hp "hp"))))

(defn fight [attacker defender]
  (let [dmg (attack attacker defender)
        defender-after-attack (update-health defender dmg)]
    (print-attack attacker defender dmg)
    (if (<= (:health (:stats defender-after-attack)) 0)
      (println (:name attacker) "won")
      (recur defender-after-attack attacker))))

