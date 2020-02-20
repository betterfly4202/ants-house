package com.ants.batch.domain.stockinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by betterfly
 * Date : 2019.04.20
 */
@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo, Long>, StockInfoRepositoryCustom {
}
