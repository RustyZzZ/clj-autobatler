(ns code.utils
  (:require
   [clojure.edn :as edn]
   [clojure.java.io :as io]))

(defn read-properties [file-path]
  (with-open [stream (io/input-stream file-path)
              reader (io/reader stream)]
    (edn/read-string (slurp reader))))

(comment
  (read-properties "resources/coeficients.edn"))


