package com.ants.batch.domain.stockinfo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * Created by betterfly
 * Date : 2019.04.24
 */
public class StockInfoRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public StockInfoRepositorySupport(JPAQueryFactory queryFactory) {
        super(StockInfo.class);
        this.queryFactory = queryFactory;
    }
}