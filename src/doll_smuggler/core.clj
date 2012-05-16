(ns doll-smuggler.core
    :use [clojure.string :only [split split-lines]])


(defrecord Doll [id weight value])



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



(defn calculate-solution-matrix
  "Calculates the matrix for the optimal solution."
  [dolls weight-limit]
  
  (loop [solutions [] row [] i -1 w 1]
        (if (= i (count dolls))
            solutions
                (if (> w weight-limit)
                      (recur (conj solutions row) [] (inc i) 1)
                      (if (< i 0)
                          (recur solutions (conj row [0 0]) i (inc w)) 
                          (let [doll-i (nth dolls i)]
                               (if (< w (:weight doll-i))
                                   (recur solutions (conj row [(first (nth (nth solutions i) (- w 1))) 0]) i (inc w))
                                   (if (= w (:weight doll-i))
                                       (if (> (:value doll-i) (first (nth (nth solutions i) (- w 1))))
                                           (recur solutions (conj row [(:value doll-i) 1]) i (inc w))
                                           (recur solutions (conj row [(first (nth (nth solutions i) (- w 1))) 0]) i (inc w)))
                                       (let [cv (+ (:value doll-i) (first (nth (nth solutions i) (- (- w (:weight doll-i)) 1))))]
                                            (if (> (first (nth (nth solutions i) (- w 1))) cv)
                                                (recur solutions (conj row [(first (nth (nth solutions i) (- w 1))) 0]) i (inc w))
                                                (recur solutions (conj row [cv 1]) i (inc w))))))))))))



(defn pack-dolls
  "Selects the optimal subset of dolls from the given set with the given weight constraint."
  [dolls weight-limit]
  
  (cond
    (= (count dolls) 0) []
    
    (= weight-limit 0) []
    
    true (let [solutions (calculate-solution-matrix dolls weight-limit)]
              (loop [handbag [] w (- weight-limit 1) i (- (count solutions) 1)]
                    (if (= i 0)
                        handbag
                        (if (= (last (nth (nth solutions i) w)) 1)
                            (recur (conj handbag (nth dolls (- i 1))) (- w (:weight (nth dolls (- i 1)))) (dec i))
                            (recur handbag w (dec i))))))))


(defn main
  [file-name weight-limit]
  
  (println "The payload for the day is:")
  (let [handbag (pack-dolls (load-dolls file-name) weight-limit)]
       (doseq [doll handbag]
              (println (:id doll)))
       handbag))