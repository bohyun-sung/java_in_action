package javaInAction.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class Main2 {

    public static void main(String[] args) {
        List<String> str = Arrays.asList("a","b","A","B");
        // 동일
        str.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);
        System.out.println("str = " + str);

//      1:  (args) -> ClassName.staticMethod(args) => ClassName::staticMethod

//      2:  (arg0, rest) -> arg0.instanceMethod(rest) => ClassName::instanceMethod

//      3:  (args) -> expr.instanceMethod(args) => expr::instanceMethod

        Function<String, Integer> integerInteger = Integer::parseInt;
        BiPredicate<List<String>, String> listObjectBooleanBiFunction = List::contains;

        Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();
        Function<Integer, Apple> c2 = Apple::new;

        c2.apply(100);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        apples.forEach(apple1 -> System.out.println(apple1.getWeight()));

        BiFunction<Integer, Color, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(110, Color.GREEN);

    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        list.forEach(l -> result.add(f.apply(l)));
        return result;
    }

}
