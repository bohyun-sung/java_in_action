package javaInAction.chapter9;

public interface Subject {

    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
