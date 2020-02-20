package com.ants.batch.domain.stockinfo;

import java.util.List;

/**
 * Created by betterfly
 * Date : 2019.04.24
 */
public interface StockInfoRepositoryCustom {
    StockInfo findByStockInfoQueryAdvance(String code, String company);
}
