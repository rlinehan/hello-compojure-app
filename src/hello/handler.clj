(ns hello.handler
  (:require [compojure.core :as c]
   :require [puppetlabs.trapperkeeper.core :refer [defservice]]))


;(ns hello.handler
;  (:use compojure.core)
;  (:require [compojure.handler :as handler]
;            [compojure.route :as route]))

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

(c/defroutes app-routes
  (c/GET "/" [] "Hello World")
  (c/GET "/thing" [] "things and stuff")
  (c/GET "/function" [] (sample))
  (c/GET "/thing/:id" [id] (str id (pluralize (parse-int id) "thing")))
  (c/GET "/:word/:id" [word id] (str id (pluralize (parse-int id) word)))
  (c/GET "/inflect/:word/:id" [word id & params]
       (str id (inflect (parse-int id) word (:plural params)))))
;  (route/resources "/")
;  (route/not-found "Not Found"))

;(def app
;  (handler/site app-routes))

(defservice hello-service
  [[:WebserverService add-ring-handler]]
  (init [this service-context]
    (let [endpoint "/hello"
          context-app (c/context endpoint [] app-routes)]
      (add-ring-handler context-app endpoint))
    service-context))
