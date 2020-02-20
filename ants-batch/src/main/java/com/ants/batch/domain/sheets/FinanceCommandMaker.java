package com.ants.batch.domain.sheets;

import com.ants.batch.domain.stockinfo.StockInfo;
import lombok.Builder;

/**
 * Created by betterfly
 * Date : 2019.05.05
 */

/**
 *  GOOGLEFINANCE(시세_표시, [속성], [시작일], [종료일|일수], [간격])
 */
public class FinanceCommandMaker {
    private final StringBuilder baseFormat = new StringBuilder("=GOOGLEFINANCE(\"%s\"");

    private String stockSubject;
    private String attributes;
    private String startDate;
    private String endDate;
    private Integer interval;

    public FinanceCommandMaker(){

    }
    @Builder
    public FinanceCommandMaker(String stockSubject, String attributes, String startDate, String endDate, Integer interval) {
        this.stockSubject = stockSubject;
        this.attributes = attributes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
    }

    private String makeBaseFormat(){
        if (stockSubject == null){
            throw new RuntimeException();
        }

        if (attributes != null){
            appendAttributes(", \"%s\"");
        }

        if (startDate != null){
            appendAttributes(", %s");
        }

        if (endDate != null){
            appendAttributes(", %s");
        }

        if (interval != null){
            appendAttributes(", %d");
        }

        appendAttributes(")");

        return baseFormat.toString();
    }

    private StringBuilder appendAttributes(String value){
        return baseFormat.append(value);
    }

    public String getCommand(){
        return String.format(makeBaseFormat(), stockSubject, attributes, startDate, endDate);
    }

    public String parseToStockSubject(StockInfo stockInfo){
        String stockSubject = null;
        switch (stockInfo.getType()){
            case 1 : stockSubject = "KRX:";
                break;
            case 2 : stockSubject = "KOSDAQ:";
                break;
            default: stockSubject = "";
                break;
        }

        return stockSubject + stockInfo.getCode();
    }
}
