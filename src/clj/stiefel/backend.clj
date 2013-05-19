(ns stiefel.backend
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes stiefel-routes
  (route/resources "/")
  (route/not-found "Page not found"))

(def app (handler/site stiefel-routes))