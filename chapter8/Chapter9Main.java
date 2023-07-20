package javaInAction.chapter8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javaInAction.chapter4.Trader;
import javaInAction.chapter4.Transaction;

public class Chapter9Main {

    public static void main(String[] args) {
        String MILAN = "Milan";
        String CAMBRIDGE = "Cambridge";

        Trader raoul = new Trader("Raoul", CAMBRIDGE);
        Trader mario = new Trader("Mario", MILAN);
        Trader alan = new Trader("Alan", CAMBRIDGE);
        Trader brian = new Trader("Brian", CAMBRIDGE);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        Map<String, Integer> entries = Map.ofEntries(
                Map.entry("Raphael", 30),
                Map.entry("Olivia", 25),
                Map.entry("Thibaut", 26)
        );

        System.out.println("entries = " + entries);

//        List<String> actors = List.of("Keanu", "Jessica");
//        actors.set(0, "Brad");
//        System.out.println("actors = " + actors);


        transactions.removeIf(transaction -> Character.isDigit(transaction.getTrader().getCity().charAt(0)));
        System.out.println("transactions = " + transactions);

        Map<String, Integer> ageOfFriends = Map.ofEntries(
                Map.entry("A", 10),
                Map.entry("B", 20),
                Map.entry("C", 30)
        );

        for (Map.Entry<String, Integer> entry : ageOfFriends.entrySet()) {
            String friend = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(friend + " is " + age + " years old");
        }

        System.out.println();
        // BiConsumer(키와 값을 인수로 받음) 아래와 같이 구현 가능
        ageOfFriends.forEach((friend, age) -> System.out.println(friend + " is " + age + " years old"));

        Map<String, String> favouriteMovies = Map.ofEntries(
                Map.entry("Raphael", "Star Wars"),
                Map.entry("Cristina", "Matrix"),
                Map.entry("Olivia", "James Bond")
        );

        favouriteMovies
                .entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .forEachOrdered(System.out::println);   // 사람의 이름을 알파벳 순으로 스트림 요소를 처리한다.

    /**
     * 계산 패턴
     * 맵에 키가 존재하는지 여부에 따라 어떤 동작을 실행하고 결과를 저장해야 하는 상황이 필요한 때가 있다
     * 예를 들어 키를 이용해 값비싼 동작을 실행해서 얻은 결과를 캐시하려 한다
     * computeIfAbsent : 제공된 키에 해당하는 값이 없으면 (값이 없거나 널), 키를 이용해서 새값을 계산하고 맵에 추가한다
     * computeIfPresent : 제공된 키가 존재하면 새 값을 계산하고 맵에 추가한다
     */
//        String friend = "Raphael";
//        List<String> movies = friendsToMovies.get(friend);
//        if (movies == null) {
//            movies = new ArrayList<>();
//            friendsToMovies.put(friend, movies);
//        }
//        movies.add("Star Wars");

//        friendsToMovies.computeIfAbsent("Raphael", name -> new ArrayList()).add("Star Wars");

        Map<String, Long> moviesToCount = new HashMap<>();
        String movieName = "JamesBond";
        Long count = moviesToCount.get(movieName);
        if (count == null) {
            moviesToCount.put(movieName, 1L);
        } else {
            moviesToCount.put(movieName, count + 1L);
        }

        moviesToCount.merge(movieName, 1L, (key, count2) -> count2 + 1L);

    }





}
