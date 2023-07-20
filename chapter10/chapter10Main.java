package javaInAction.chapter10;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javaInAction.chapter1.Color;

public class chapter10Main {

    public static void main(String[] args) {

//        Collector<? super Car, ?, Map<Brand, Map<Color, List<Car>>>> carGrouping = GroupingBuilder.groupOn(
//                Car::getColor).after(Car::getBrand).get();
        Order order = MethodChainingOrderBuilder.forCustomer(" Big Bank ")
                .buy(80)
                .stock( "IBM" )
                .on( "NYSE" )
                .at(125.00)
                .sell(50)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();

//        Order order1 = order(
//                "BigBank",
//                buy(80, stock("IBM", on("NYSE")), at(125.00)),
//                sell(50, stock("GOOGLE", on(NASDAQ)), at(375.00))
//                );
    }
}
