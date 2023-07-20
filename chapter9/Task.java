package javaInAction.chapter9;

interface Task {
    public void execute();

    public static void doSomething(Runnable r) {r.run();}
    public static void doSomething(Task a) {a.execute();}


}
