(ns doll-smuggler.core
    :use [clojure.string])


(defrecord Doll [id weight value])

(defn main
  
  )



(defn load-dolls
  "Takes a CSV data file and creates a vector of Doll records from it.
  **NOTE** does no error checking on the file."
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
  "Selects the optimal subset of dolls from the given set with the given weight constraint."
  [dolls weight-limit]
  
  (cond
    (= (count dolls 0)) []
    (= weight-limit 0) []
    true (let [keep (calculate-keep-matrix dolls weight-limit)]
              ))))



(defn calculate-solution-matrix
  "Calculates the keep matrix for the optimal solution."
  [dolls weight-limit]
  
  (loop [solutions [] row [] i 0 w 0]
        (if (= i (count dolls))
            keep
            (let [doll-i (nth dolls i)]
                 )