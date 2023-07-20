package javaInAction.chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javaInAction.chapter4.Dish;
import javaInAction.chapter4.Dish.Type;
import javaInAction.chapter4.Transaction;

public class Chapter6Main {

    public static void main(String[] args) {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        List<Dish> menu = Arrays.asList(
                new Dish("A", true, 300, Type.MEAT),
                new Dish("B", true, 200, Type.MEAT),
                new Dish("C", true, 100, Type.MEAT),
                new Dish("D", true, 100, Type.OTHER)
        );
        IntSummaryStatistics intSummaryStatistics = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

//        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("shortMenu = " + shortMenu);

        menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

        Map<Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println("dishesByType = " + dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(
                        dish -> {
                            if (dish.getCalories() == 300) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() == 200) {
                                return CaloricLevel.NOMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }
                ));
        System.out.println("dishesByCaloricLevel = " + dishesByCaloricLevel);

        System.out.println(
                "dishesByCaloricLevel.get(CaloricLevel.FAT) = " + dishesByCaloricLevel.get(
                        CaloricLevel.FAT));

        System.out.println(
                "menu.stream().filter(dish -> dish.getCalories() >= 200).collect(Collectors.groupingBy(Dish::getType)) = "
                        +
                        menu.stream().filter(dish -> dish.getCalories() >= 200)
                                .collect(Collectors.groupingBy(Dish::getType)));

        // 필터 프레디케이트를 만족하는 OTHER 종류 요리는 없으므로 결과 맵에서 해당 키 자체가 사라진다
        Map<Type, List<Dish>> caloricDishesByType = menu.stream()
                .collect(
                        Collectors.groupingBy(Dish::getType,
                                Collectors.filtering(dish -> dish.getCalories() >= 200,
                                        Collectors.toList())));

        System.out.println("caloricDishesByType = " + caloricDishesByType);

        Map<Type, List<String>> dishNamesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.mapping(Dish::getName, Collectors.toList())));
        System.out.println("dishNamesByType = " + dishNamesByType);

        menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType, Collectors.maxBy(
                                Comparator.comparingInt(Dish::getCalories))));

        System.out.println("mostCaloricByType = " + mostCaloricByType);

        // 서브그룹에서 가장 칼로리가 높은 요리 찾기
        Map<Type, Dish> mostCaloricByType2 = menu.stream()
                .collect(Collectors.groupingBy(
                        Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(
                                        Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));

        Map<Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(dish -> {
                    if (dish.getCalories() == 300) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() == 200) {
                        return CaloricLevel.NOMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                        }, Collectors.toSet()
                )));
        System.out.println("caloricLevelsByType = " + caloricLevelsByType);

        Map<Boolean, List<Dish>> partitioneMenu = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println("partitioneMenu = " + partitioneMenu);
        System.out.println("partitioneMenu.get(true) = " + partitioneMenu.get(true));
        System.out.println("partitioneMenu.get(false) = " + partitioneMenu.get(false));

        List<Dish> dishes = menu.stream().collect(new ToListCollector<Dish>());
        System.out.println("dishes = " + dishes);
        menu.stream().collect(ArrayList::new, List::add, List::addAll);

    }

    public boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }

}
