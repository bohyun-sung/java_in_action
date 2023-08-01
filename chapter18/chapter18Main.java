package javaInAction.chapter18;

public class chapter18Main {

    public static void main(String[] args) {
    }

    static int factorialIterative(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

    // 재귀 방식의 팩토리얼
    static long factorialRecursive(long n) {
        return n == 1 ? 1 : n * factorialRecursive(n - 1);
    }
}
