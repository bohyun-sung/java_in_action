package javaInAction.chapter9;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chapter9Main {

    public static void main(String[] args) {

//        Runnable r1 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hello");
//            }
//        };
//        Runnable r2 = () -> System.out.println("Hello");

        /**
         * 1. 람다에서 this는 람다를 감싸는 클래스를 가리킨다
         * 2. 익명클래스는 감싸고 있는 클래스의 변수를 가릴 수 있다
         */
//        int a = 10;
//        Runnable r1 = () -> {
//            int a = 2;
//            System.out.println(a);
//        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println("a = " + a);
            }
        };
        r2.run();

        Validator numbericValidator = new Validator(new IsNumeric());
        boolean b1 = numbericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);

        new OnlineBanking() {
            @Override
            void makeCustomerHappy(Customer c) {

            }
        }.processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName()));

        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favorite book is Modern Java in Action!");
        // 람다 표현식
        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println(" Breaking news in NY! " + tweet);
            }
        });

        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains(" queen ")) {
                System.out.println(" Yet more news from London... " + tweet);
            }
        });
        f.notifyObservers("money");

        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't labda really sexy?!!");
        System.out.println("result = " + result);

        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: "
                + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Aren ' t labda really sexy?!!");
        System.out.println("result2 = " + result2);

        /**
         * case문은
         * final static Map<String, Supplier<Product>> map = new HashMap<>();
         * static {
         *      map.put("lona", Loan::new);
         *      map.put("stock", Stock::new);
         * }
         * 으로 인스턴트화 할수있다
         */


    }


}
