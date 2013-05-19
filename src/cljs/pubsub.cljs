(ns stiefel.pubsub
  (:require [shoreleave.pubsubs.simple :as pbus]
            [shoreleave.pubsubs.protocols :as pubsub]
            [shoreleave.pubsubs.publishable :as publishable]))

(def ^:private bus (pbus/bus))

(defn topic [f]
  (pubsub/publishize f bus))

(defn subscribe [topics & fs]
  (doseq [f fs]
    (pubsub/subscribe bus topic f)))