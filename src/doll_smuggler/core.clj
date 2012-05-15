(ns doll-smuggler.core
    :use [clojure.string])




(defn main
  
  )



(defn load-dolls
  [file-name]
  (let [doll-data (split-lines (slurp file-name))]
       (loop [i 0 payload {}]
             (if (> i (count doll-data))
                payload
                (let [line (split (nth doll-data i) #",")]
                      (recur (inc i) 
                              (conj payload [(first line) 
                                            [(Integer/parseInt (second line)) 
                                              (Integer/parseInt (last line))]])))))))


(defn pack-dolls
  [dolls weight-limit])

