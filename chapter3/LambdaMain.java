package javaInAction.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import javaInAction.chapter1.Apple;
import javaInAction.chapter1.Color;

public class LambdaMain {

    public static void main(String[] args) {
        /**
         * 기존 구현된 메서드를 재활용하여 메서드 참조 방법
         */
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println("str = " + str);
        List<String> str2 = Arrays.asList("a","b","A","B");
        str2.sort(String::compareToIgnoreCase);
        System.out.println("str2 = " + str2);

        ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
        ToIntFunction<String> stringToInt2 = Integer::parseInt;

        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        BiPredicate<List<String>, String> contains2 = List::contains;

        String a = "s";
        Predicate<String> startsWithNumber = (String string) -> a.startsWith(string);
        Predicate<String> startsWithNumber2 = a::startsWith;

        /**
         * 클래스 생성자를 사용하는 방법
         */

        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get(); // Supplier의 get 메서드를 호출해서 새로운 Apple 객체를 만들 수 있다

        Function<Integer, Apple> c2 = Apple::new;
        // Fuction의 apply 메서드에 무게를 인수로 호출해서 새로운 Apple 객체를 만들 수 있다
        Apple a2 = c2.apply(110);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        BiFunction<Color, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(Color.GREEN, 110);

        Apple redApple = c3.apply(Color.RED, 100);
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        list.forEach(i -> result.add(f.apply(i)));
        return result;
    }


}
