package javaInAction.chapter1;

// 무거운 사과만
public class AppleHeavyWeightPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
