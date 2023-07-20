package javaInAction.chapter7;

import java.util.stream.Stream;

public class Chapter7Main {

    public static void main(String[] args) {

    }

    public long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel() // 스티림을 병렬 스트림으로 변환
                .reduce(0L, Long::sum);
    }


}
