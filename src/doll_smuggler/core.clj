(ns doll-smuggler.core
    :use [clojure.string])


(defrecord Doll [id weight value])

(defn main
  
  )



(defn load-dolls
  ""
  [file-name]
  (let [doll-data (split-lines (slurp file-name))]
      (loop [payload [] i 0]
            (if (= i (count doll-data))
                payload
                (let [line (split (nth doll-data i) #",")]
                      (recur (conj payload (Doll. (first line) 
                                                  (Integer/parseInt (second line)) 
                                                  (Integer/parseInt (last line))))
                            (inc i)))))))


(defn pack-dolls
  [dolls weight-limit]
  (cond
    (= (count dolls 0)) '()
    (= weight-limit 0) '()
    true ()))

