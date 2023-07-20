package javaInAction.chapter6;

import static javaInAction.chapter6.PrimeNumbersCollector.isPrime;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectorHarness {

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.range(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }
}
