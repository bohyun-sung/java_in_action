package javaInAction.chapter9;

public class Database {

    public static Customer getCustomerWithId(int id) {
        return new Customer(String.valueOf(id));
    }
}
