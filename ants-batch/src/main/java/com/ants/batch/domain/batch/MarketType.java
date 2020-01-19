package com.ants.batch.domain.batch;

/**
 * Created by betterfly
 * Date : 2019.04.22
 */
public enum MarketType {
    STOCK_MARKET(1, "stockMkt"),
    KOSDAQ_MARKET(2, "kosdaqMkt"),
    KONEX_MARKET(3, "konexMkt")
    ;

    private int type;
    private String stockName;

    MarketType(int type, String stockName){
        this.type = type;
        this.stockName = stockName;
    }

    public int getType() {
        return type;
    }

    public String getStockName() {
        return stockName;
    }
}
