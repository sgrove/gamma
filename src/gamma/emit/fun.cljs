(ns gamma.emit.fun
  (:use [gamma.emit.emit :only [emit]]
        [gamma.ast :only [head body term ]]))


;;;; FUNCTIONS



(defmethod emit :function [db x]
  [:group
   (name (head x))
   "("
   [:line ""]
   [:nest 2
    (interpose [:span "," :line]
               (map #(emit db (db %)) (body x)))]
   ")"])

(defmethod emit :swizzle [db x]
  [:span (emit db (db (first (body x))))  "." (name (:swizzle x))])


(comment
  (defmethod emit :function [db x]
   (apply
     str
     (flatten [(name (head x))
               "("
               (interpose ", " (map emit (body x)))
               ")"]))))

(comment
  (use 'gamma.emit.function)
  (in-ns 'gamma.emit.function)



  (fipp.printer/pprint-document
    (emit (term :max 1 (term :max 2 3 4))) {:width 15})

  )