package javaInAction.chapter19;

import java.util.function.Function;
import java.util.function.Supplier;

public class Chapter19Main {

    public static void main(String[] args) {
        LazyList<Integer> numbers = LazyList.from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);
    }

    public static <T> T myIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

//    static <T> T patternMatchExpr(
//            Expr e,
//            TryFunction<String, Expr, Expr, T> binopcase,
//            Function<Integer, T> numcase,
//            Supplier<T> defaultcase) {
//        return
//                (e instanceof BinOp) ?
//                        binopcase.apply(((BinOp)e).opname, ((BinOp)e).left, ((BinOp)e).right :)
//    }
}
