(ns hello.test.handler
  (:use clojure.test
        ring.mock.request
        hello.handler))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "thing route singular"
    (let [response (app (request :get "/thing/1"))]
      (is (= (:status response) 200))
      (is (= (:body response) "1 thing"))))

  (testing "thing route plural"
    (let [response (app (request :get "/thing/2"))]
      (is (= (:status response) 200))
      (is (= (:body response) "2 things"))))

  (testing "word route singular"
    (let [response (app (request :get "/cat/1"))]
      (is (= (:status response) 200))
      (is (= (:body response) "1 cat"))))

  (testing "word route plural"
    (let [response (app (request :get "/cat/2"))]
      (is (= (:status response) 200))
      (is (= (:body response) "2 cats"))))

  (testing "inflect route singular"
    (let [response (app (request :get "/inflect/box/1?plural=boxes"))]
      (is (= (:status response) 200))
      (is (= (:body response) "1 box"))))

  (testing "inflect route plural"
    (let [response (app (request :get "/inflect/box/2?plural=boxes"))]
      (is (= (:status response) 200))
      (is (= (:body response) "2 boxes")))))
