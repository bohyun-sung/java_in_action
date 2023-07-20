package javaInAction.chapter10;

import javaInAction.chapter10.Trade.Type;

public class TradeBuilder {
    private final MethodChainingOrderBuilder builder;

    public final Trade trade = new Trade();

    public TradeBuilder(MethodChainingOrderBuilder builder, Type type, int quantity) {
        this.builder = builder;
        trade.setType(type);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(builder, trade, symbol);
    }
}
