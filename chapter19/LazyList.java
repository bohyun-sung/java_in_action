package javaInAction.chapter19;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {

    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get(); // Supplier로 게으른 동작을 만들었다
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<Integer>(n, () -> from(n + 1));
    }



    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail()
                                .filter(n -> n % numbers.head() != 0)
                )
        );
    }


}
