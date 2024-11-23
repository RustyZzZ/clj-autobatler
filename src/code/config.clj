(ns code.config
  (:require [code.utils :refer [read-properties]]))

(def coef-by-race
  (read-properties "resources/coeficients.edn"))

(def stat-config
  (read-properties "resources/stats-configuration.edn"))

(defn calc-stat [attrs coef attr-key stat-key]
  (let [stat-coef (or (stat-key coef) 0)]
    (* (attr-key attrs) stat-coef)))

(defn calc-stat-partial [attrs coef]
  #(calc-stat attrs coef %1 %2))

(defn map-to-pairs [m]
  (mapcat #(let [[attr stats] %]
             (map (fn [stat]
                    {:attr attr :stat stat})
                  stats))
          m))
(defn calculate-stats
  [attrs coefs stat-config]
  (let [calc-fn (calc-stat-partial attrs coefs)]
    (->> stat-config
         map-to-pairs
         (map #(calc-fn (:attr %) (:stat %))))))
