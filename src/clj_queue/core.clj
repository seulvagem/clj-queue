(ns clj-queue.core)

(defn queue
  ([]
   (clojure.lang.PersistentQueue/EMPTY))
  ([coll]
   (into (queue) coll)))

(defn queue?
  [x]
  (= (type x) clojure.lang.PersistentQueue))



;; overwrite default printer for queues
(defmethod print-method clojure.lang.PersistentQueue [q, w] ; Overload the printer for queues so they look like fish
  (print-method '<- w)
  (print-method (seq q) w)
  (print-method '-< w))