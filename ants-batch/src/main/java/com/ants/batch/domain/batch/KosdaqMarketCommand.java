package com.ants.batch.domain.batch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by betterfly
 * Date : 2019.04.20
 */
@Slf4j
@AllArgsConstructor
@Service
public class KosdaqMarketCommand implements Command {
    @Autowired
    private StockBatchExecutor stockBatchExecutor;

    @Override
    public void execute() {
        stockBatchExecutor.executeStockMarket(MarketType.KOSDAQ_MARKET);
    }
}
