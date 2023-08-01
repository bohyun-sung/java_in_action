package javaInAction.chapter10.lambda;

import java.util.function.Consumer;
import javaInAction.chapter10.Order;
import javaInAction.chapter10.Trade.Type;


public class LambdaOrderBuilder {
    private Order order = new Order();  // 빌더로 주문을 감쌈

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder);   // 주문 빌더로 전달된 람다 표현식 실행
        return builder.order;   // OrderBuilder의 Consumer를 실행해 만들어진 주문을 반환
    }

    public void forCustomer(String customer) {
        order.setCustomer(customer);
    }

    public void buy(Consumer<TradeBuilder> consumer) {
        trade(consumer, Type.BUY);
    }

    public void sell(Consumer<TradeBuilder> consumer) {
        trade(consumer, Type.SELL);
    }

    private void trade(Consumer<TradeBuilder> consumer, Type type) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType(type);
        consumer.accept(builder);   // TradeBuilder로 전달할 람다 표현식 실행
        order.addTrade(builder.trade);  // TradeBuilder의 Consumer를 실행해 만든 거래를 주문에 추가가
    }

}

