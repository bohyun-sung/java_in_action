package javaInAction.chapter9;

public class IsAllLowerCase implements ValidationStrategy {

    @Override
    public boolean execute(String e) {
        return e.matches("[a-z]+");
    }
}
