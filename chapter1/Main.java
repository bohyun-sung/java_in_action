package javaInAction.chapter1;

import static javaInAction.chapter1.Color.GREEN;
import static javaInAction.chapter1.Color.RED;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//        test();
//        List<Apple> inventory = List.of(
//                new Apple(150, GREEN),
//                new Apple(160,GREEN),
//                new Apple(140, RED),
//                new Apple(170, RED)
//        );
//        List<Integer> nunber = List.of(1,2,3,4,5,6);
//        List<Integer> evenNumbers = filter(nunber, (Integer i) -> i % 2 == 0);
//
//        List<Apple> redApples = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
//
//        inventory.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight().compareTo(o2.getWeight());
//            }
//        });
//        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

//        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
//        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
//        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
//            @Override
//            public boolean test(Apple apple) {
//                return RED.equals(apple.getColor());
//            }
//        });
//        List<Apple> result = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));
//        redApples.forEach(Apple::printColor);
//        List<Apple> apples = filterGreenApples(inventory);
//        apples.forEach(Apple::printColor);
//        List<Apple> greenApples = filterApplesByColor(inventory, GREEN);
//        List<Apple> redApples = filterApplesByColor(inventory, RED);
//        greenApples.forEach(Apple::printColor);
//        redApples.forEach(Apple::printColor);
//
//        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
//        redAndHeavyApples.forEach(Apple::printColor);

        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        ToIntBiFunction<Apple, Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(
                a2.getWeight());
        BiFunction<Apple, Apple, Integer> c3 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(
                a2.getWeight());
        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);
        System.out.println("c3 = " + c3);

    }


    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
    // test1
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
    // 디폴트 메서드와 자바 모듈
    private static void test(){
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(150, GREEN));
        inventory.add(new Apple(160,GREEN));
        inventory.add(new Apple(140, RED));

        List<Apple> heavyApples1 = inventory.stream().filter((Apple a) -> a.getWeight() > 150).collect(
                Collectors.toList());

        List<Apple> heavyApples2 = inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150).collect(
                Collectors.toList());

        heavyApples1.forEach(System.out::println);
        heavyApples1.forEach(Apple::printWeight);
        heavyApples2.forEach(System.out::println);
        heavyApples2.forEach(Apple::printWeight);
    }

    // List에 직접 sort 메서드를 호출할 수 있다
//    default void sort(Comparator<? super E> c) {
//        Collections.sort(this, c);
//
//        List list = new ArrayList<>();
//        list.sort();
//    }


}
