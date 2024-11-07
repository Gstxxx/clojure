(ns test
  (:require
   [clojure.set :as set]
   [clojure.string :as str]))

(defn generate-random-set []
  (set (take (rand-int 10) (repeatedly #(rand-nth [:a :b :c :d :e :f :g :h :i :j])))))

(defn perform-set-operations [set1 set2]
  {:intersection (set/intersection set1 set2)
   :difference (set/difference set1 set2)
   :union (set/union set1 set2)
   :symmetric-difference (set/difference (set/union set1 set2) (set/intersection set1 set2))})

(defn create-nested-maps [depth]
  (if (zero? depth)
    (generate-random-set)
    {(keyword (str "level-" depth)) (create-nested-maps (dec depth))}))

(defn deep-merge [& maps]
  (if (every? map? maps)
    (apply merge-with deep-merge maps)
    (last maps)))

(defn transform-sets [input-set]
  (->> input-set
       (map #(hash-map % (count (str %))))
       (reduce merge)))

(defn fibonacci [n]
  (loop [a 0 b 1 series []]
    (if (>= (count series) n)
      series
      (recur b (+ a b) (conj series a)))))

(defn process-data-structure [input]
  (letfn [(process-element [elem]
            (cond
              (set? elem) (transform-sets elem)
              (map? elem) (reduce-kv #(assoc %1 %2 (process-element %3)) {} elem)
              (sequential? elem) (mapv process-element elem)
              :else elem))]
    (process-element input)))

(defn generate-complex-structure []
  (let [base-sets [(generate-random-set) (generate-random-set) (generate-random-set)]
        nested-structure (create-nested-maps 5)
        processed-data (process-data-structure nested-structure)
        fib-sequence (fibonacci 10)]
    {:base-sets base-sets
     :set-operations (map #(perform-set-operations %1 %2) base-sets (rest base-sets))
     :nested-data processed-data
     :fibonacci fib-sequence
     :combined (deep-merge processed-data
                          {:additional-data (transform-sets (first base-sets))})}))

(defn apply-operations [data]
  (letfn [(operation-chain [x]
            (->> x
                 (map str)
                 (map #(str/split % #""))
                 (map frequencies)
                 (apply merge-with +)))]
    (->> data
         (map (fn [[k v]]
                [k (if (set? v)
                     (operation-chain v)
                     v)]))
         (into {}))))

(defn recursive-transform [input depth]
  (if (zero? depth)
    input
    (let [transformed (if (map? input)
                       (update-vals input #(recursive-transform % (dec depth)))
                       (if (set? input)
                         (perform-set-operations input (generate-random-set))
                         input))]
      (if (map? transformed)
        (apply-operations transformed)
        transformed))))

(defn execute-complex-operations []
  (let [initial-structure (generate-complex-structure)
        transformed-structure (recursive-transform initial-structure 3)
        result-sets (map generate-random-set (range 5))
        combined-results (->> result-sets
                            (partition 2 1)
                            (map #(apply perform-set-operations %))
                            (reduce deep-merge))]
    {:initial initial-structure
     :transformed transformed-structure
     :result-sets result-sets
     :combined-results combined-results
     :final-merge (deep-merge transformed-structure
                             combined-results
                             {:timestamp (System/currentTimeMillis)})}))

(defn process-and-aggregate [n]
  (->> (range n)
       (map (fn [_] (execute-complex-operations)))
       (reduce (fn [acc result]
                (merge-with (fn [a b]
                             (cond
                               (and (map? a) (map? b)) (deep-merge a b)
                               (and (set? a) (set? b)) (set/union a b)
                               :else b))
                           acc
                           result))
               {})))

(def final-result (process-and-aggregate 3))

(println final-result)
