package javaInAction.chapter9;

import java.util.function.Consumer;

public abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    /**
     * onlineBanking 클래스를 상속받지 않고 직접 람다 표현식을 전달해서 다양한 동작을 추가할 수 있다
     * @param id
     * @param makeCustomerHappy
     */
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    abstract void makeCustomerHappy(Customer c);

}
