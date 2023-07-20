package javaInAction.chapter1;

public class AppleRedAndHeavyPredicate implements ApplePredicate {


    @Override
    public boolean test(Apple apple) {
        return Color.RED.equals(apple.getColor()) && apple.getWeight() > 150;
    }
}