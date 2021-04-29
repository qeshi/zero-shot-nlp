(ns zero-shot-nlp.core
  (:gen-class)
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python :as py :refer [py. py.. py.-]]))

(require-python '[transformers :bind-ns])

(require '[clojure.pprint :as p])

(use '[clojure.java.shell :only [sh]])

;; (in-ns 'clojure.pprint)

(defn -main
  "I classify stuff."
  [& args]
  (def classifier (py. transformers "pipeline" "zero-shot-classification"))

  (def text "French Toast with egg and bacon in the center with maple syrup on top. Sprinkle with powdered sugar if desired.")

  (def labels ["breakfast" "lunch" "dinner"])

  (let [s (classifier text labels)
        j (clojure.data.json/write-str s)]
    (sh "sh" "-c" "jq . | tv" :in j))

  ;; (p/write (classifier text labels))
  ;; (let [out (java.io.StringWriter.)
  ;;       s (classifier text labels)]
  ;;   ;; (clojure.data.json/pprint-json)
  ;;   ;; (p/pprint s out)
  ;;   (p/pprint s out)
  ;;   ;; (p/pprint {:c 3 :d 4} out)
  ;;   (sh "sh" "-c" "jq . | tv" :in (.toString out)))
  )