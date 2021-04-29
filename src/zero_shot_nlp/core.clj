(ns zero-shot-nlp.core
  (:gen-class)
  (:require [libpython-clj2.require :refer [require-python]]
            [libpython-clj2.python :as py :refer [py. py.. py.-]]))

(require-python '[transformers :bind-ns])

(defn -main
  "I classify stuff."
  [& args]
  (def classifier (py. transformers "pipeline" "zero-shot-classification"))

  (def text "French Toast with egg and bacon in the center with maple syrup on top. Sprinkle with powdered sugar if desired.")

  (def labels ["breakfast" "lunch" "dinner"])

  (classifier text labels))
