package javaInAction.chapter4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javaInAction.chapter4.Dish.Type;

public class Chapter4Main {

    public static void main(String[] args) {
//        List<Dish> specialMenu = Arrays.asList(
//                new Dish("seasonal fruit", true, 120, Type.OTHER),
//                new Dish("prawns", false, 300, Type.FISH),
//                new Dish("rice", true, 350, Type.OTHER),
//                new Dish("chicken", false, 400, Type.MEAT),
//                new Dish("french fries", true, 530, Type.OTHER)
//        );
//
//        // filter
//        List<Dish> filterMenu = specialMenu.stream().filter(dish -> dish.getCalories() < 320)
//                .collect(Collectors.toList());
//
//        // true 인것
//        specialMenu.stream()
//                .takeWhile(dish -> dish.getCalories() < 320)
//                .collect(Collectors.toList());
//        // false 인것
//        specialMenu.stream()
//                .dropWhile(dish -> dish.getCalories() < 320)
//                .collect(Collectors.toList());
//
//        String[] arrayOfWords = {"Goodbye", "World"};
//        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
//
//        streamOfwords.map(word -> word.split(""))
//                .map(Arrays::stream)
//                .distinct()
//                .collect(Collectors.toList());
//
//        List<Integer> listA = Arrays.asList(1, 2, 3);
//        List<Integer> listB = Arrays.asList(3, 4);
//        listA.stream()
//                .flatMap(a -> listB.stream()
//                        .map(b -> new int[] {a,b}))
//                .collect(Collectors.toList());
//
//

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

        // 1 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        // 2 거래자가 근무하는 모든 도시를 중복 없이 나열하시오
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        // 3 케임브리지에서 근무하는 모든 거래자를 찾아 이름순으로 정렬하시오
//        transactions.stream()
//                .filter(transaction -> Objects.equals(transaction.getTrader().getCity(), CAMBRIDGE))
//                .sorted(Comparator.comparing(transaction -> transaction.getTrader().getName()));
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals(CAMBRIDGE))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        // 4 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
//        transactions.stream()
//                .map(Transaction::getTrader)
//                .sorted(Comparator.comparing(Trader::getName));
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        // 각 반복 과정에서 모든 문자열을 반복적으로 연결해서 새로운 문자열 객체를 만든다.
        // 따라서 위 코드는 효율성이 부족하다 아래 코드와 같이 joining을 이용하는게 더 효율적이다
        // joining은 내부적으로 StringBuilder 를 이용한다
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        // 5 밀라노에 거래자가 있는가?
//        transactions.stream()
//                .filter(transaction -> Objects.equals(transaction.getTrader().getCity(), MILAN))
//                .findAny();
        transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals(MILAN));

        // 6 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오
//        transactions.stream()
//                .filter(transaction -> transaction.getTrader().getCity().equals(CAMBRIDGE))
//                .forEach(transaction -> System.out.println(transaction.getValue()));
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals(CAMBRIDGE))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        // 7 전체 트랜잭션 중 최댓값은 얼마인가?
//        transactions.stream()
//                .max(Comparator.comparing(Transaction::getValue));
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        // 8 전체 트랜잭션 중 최소값은 얼마인가?
//        transactions.stream()
//                .min(Comparator.comparing(Transaction::getValue));
        transactions.stream().reduce((transaction, transaction2) ->
                transaction.getValue() < transaction2.getValue() ? transaction : transaction2);

        // 스트림 만들기
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        // 스트림 비우기
        Stream.empty();

        // null이 될 수 있는 객체로 스트림 만들기
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        // 자바 9 부터가능
        Stream<String> homeValueStream2 = Stream.ofNullable(System.getProperty("home"));
        // flatMap과 함께 사용하면 상황에서 더 유용하게 사용가능
        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        // file stream 처리
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 무한 스트림 만들기

        // iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // 피보나치 stream
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        // 아래 코드는 종료되지않는다 filter 메소드는 언제 이 작업을 중단해야 하는지 알 수 없기 때문이다
//        IntStream.iterate(0, n -> n + 4)
//                .filter(n -> n < 100)
//                .forEach(System.out::println);

        // 아래 코드로 변경하여 스트림 쇼트서킷을 지원하는 takeWhile을 이용하는것이 해법이다
        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        System.out.println();

        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

    }
}
