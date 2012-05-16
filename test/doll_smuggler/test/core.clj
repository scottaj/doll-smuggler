(ns doll-smuggler.test.core
  (:use [doll-smuggler.core])
  (:use [clojure.test]))

(deftest zero-weight-handbag
  (testing "A handbag that can hold nothing."
           (is (= [] (main "./dolldata1" 0)))
           (is (= [] (main "./dolldata2" 0)))))

(deftest really-big-handbag
  (testing "A handbag that can hold everything."
           (is (= [(Doll.luke 9 150)
                   (Doll. anthony 13 35)
                   (Doll. candice 153 200)
                   (Doll. dorothy 50 160)
                   (Doll. puppy,15 60)
                   (Doll. thomas 68 45)
                   (Doll. randal 27 60)
                   (Doll. april 39 40)
                   (Doll. nancy 23 30)
                   (Doll. bonnie 52 10)
                   (Doll. marc 11 70)
                   (Doll. kate 32 30)
                   (Doll. tbone 24 15)
                   (Doll. tranny 48 10)
                   (Doll. uma 73 40)
                   (Doll. grumpkin 42 70)
                   (Doll. dusty 43 75)
                   (Doll. grumpy 22 80)
                   (Doll. eddie 7 20)
                   (Doll. tory 18 12)
                   (Doll. sally 4 50)
                   (Doll. babe 30 10)] (main "./dolldata1" 10000)))
           (is (= [(Doll. uno 3 5)
                   (Doll. dos 2 3)
                   (Doll. tres 1 4)] (main "./dolldata2" 10000)))))
