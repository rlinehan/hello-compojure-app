(ns hello.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn sample [] (str "Wooo function!"))
(defn plurals [word] (str word "s"))
(defn pluralize [number word]
  (if (= 1 number)
    (str " " word)
    (str " " word "s")))

(defn inflect [number word plural]
  (if (= 1 number)
    (str " " word)
    (str " " plural)))

(defn parse-int [string]
  (Integer. (re-find #"\d+" string)))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/thing" [] "things and stuff")
  (GET "/function" [] (sample))
  (GET "/thing/:id" [id] (str id (pluralize (parse-int id) "thing")))
  (GET "/:word/:id" [word id] (str id (pluralize (parse-int id) word)))
  (GET "/inflect/:word/:id" [word id & params]
       (str id (inflect (parse-int id) word (:plural params))))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
