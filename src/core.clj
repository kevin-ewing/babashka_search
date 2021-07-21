(ns core
  (:require [clojure.java.io :as io]))

(defn- get-names
  [files]
  "Get file names from sequence"
  (if (first files)
    (cons (.getName (first files))
          (get-names (rest files)))
    (list)))

(defn- is-direct
  [files]
  "Gets if it is a directory from sequence"
  (if (first files)
    (cons (.isDirectory (first files))
          (is-direct (rest files)))
    (list)))

(defn- get-path
  [files]
  "Get paths from sequence"
  (if (first files)
    (cons (.getAbsolutePath (first files))
          (get-path (rest files)))
    (list)))

(defn- get-size
  [files]
  "Get paths from sequence"
  (if (first files)
    (cons (.length (first files))
          (get-size (rest files)))
    (list)))

(defn- make-map
  "Makes a map of all files in folder"
  [seq-names seq-isdir seq-path seq-size]
  (if (first seq-names)
    (cons (hash-map :name (first seq-names)
                    :is-dir (first seq-isdir)
                    :path (first seq-path)
                    :size (first seq-size))
          (make-map (rest seq-names) (rest seq-isdir) (rest seq-path) (rest seq-size)))
    (list)))


(defn -main
  "Recursively searches home directory for all files/folder with input name"
  ([start file] (-main start file start))
  ([start file directory]
   (let [all-files (seq (.listFiles (io/file directory)))
         seq-names (get-names all-files)
         seq-isdir (is-direct all-files)
         seq-path (get-path all-files)
         seq-size (get-size all-files)
         file-map (make-map seq-names seq-isdir seq-path seq-size)
         name-matched (filter #(= (:name %) file) file-map)]
     (dotimes [x (count name-matched)]
       (println (:name (nth name-matched x)))
       (println (str "   Path: " (:path (nth name-matched x))))
       (println (str "   Size: " (:size (nth name-matched x)) " bytes")))
     (dotimes [x (count file-map)]
       (if (and (nth file-map x) (not= "Library" (:name (nth file-map x))))
         (-main start file (:path (nth file-map x))))))))
