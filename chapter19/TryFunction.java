package javaInAction.chapter19;

public interface TryFunction<S, T, U, R> {
    R apply(S s, T t, U u);
}


