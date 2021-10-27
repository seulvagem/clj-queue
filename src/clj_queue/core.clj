(ns clj-queue.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

(defn queue
  "takes a coll or nothing, returns a queue"
  ([]
   (clojure.lang.PersistentQueue/EMPTY))
  ([coll]
   (into (queue) coll)))

(def ueue
  "alias for queue"
  queue)

(defn queue?
  [x]
  (= (type x) clojure.lang.PersistentQueue))

(def ?
  "alias for queue?"
  queue?)

(def queue-gen (gen/fmap #(queue %)
                         (s/gen list?)))

(s/def ::queue (s/with-gen queue? (constantly queue-gen)))

;; overwrite default printer for queues
(defmethod print-method clojure.lang.PersistentQueue [q, w] ; Overload the printer for queues so they look like fish
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))