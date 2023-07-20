package javaInAction.chapter6;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ToListCollector<T> implements Collector<T, List<T>,List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list, list2) -> {
            list.addAll(list2);
            return list;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity(); //항등함수
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collectors.toUnmodifiableSet().characteristics();
//        return Collectors.toUnmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
