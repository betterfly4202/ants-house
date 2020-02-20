package com.ants.batch.domain.stockinfo;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

//import static com.ants.batch.domain.stockinfo.QStockInfo.stockInfo;

/**
 * Created by betterfly
 * Date : 2019.04.24
 */

@RequiredArgsConstructor
@Service
public class StockInfoRepositoryImpl implements StockInfoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public StockInfo findByStockInfoQueryAdvance(String code, String company) {
        return queryFactory
                .selectFrom(stockInfo)
                .where(eqCode(code)
                      ,likeCompany(company))
                .fetchOne();
    }

    private BooleanExpression eqCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return stockInfo.code.eq(code);
    }

    private BooleanExpression likeCompany(String company) {
        if (StringUtils.isEmpty(company)) {
            return null;
        }
        return stockInfo.company.like(company);
    }
}
