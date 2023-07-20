package javaInAction.chapter9;

public class IsNumeric implements ValidationStrategy {

    @Override
    public boolean execute(String e) {
        return e.matches("\\d+");
    }
}
