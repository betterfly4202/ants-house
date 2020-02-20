package com.ants.batch.domain.stockinfo;

import com.ants.batch.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by betterfly
 * Date : 2019.04.24
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Entity
@Data
@Table(name = "stock")
public class StockInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long stockId;

    private int type;
    private String code;
    private String company;
    private String businessType;
    private String mainProduct;
    private String listedDate;
    private String settlingMonth;
    private String president;
    private String homepage;
    private String area;

    @Builder
    public StockInfo(int type, String company, String code, String businessType, String mainProduct,
                     String listedDate, String settlingMonth, String president, String homepage, String area){
        this.type = type;
        this.company = company;
        this.code = adjustStringLength(code);
        this.businessType = businessType;
        this.mainProduct = mainProduct;
        this.listedDate = listedDate;
        this.settlingMonth = settlingMonth;
        this.president = president;
        this.homepage = homepage;
        this.area = area;
    }

    private String adjustStringLength(String value){

        final int STOCK_CODE_LENGTH = 6;

        if (value.length() < STOCK_CODE_LENGTH){
            for (int i=0; i <= STOCK_CODE_LENGTH-value.length(); i++){
                value = "0" + value;
            }
        }

        return value;
    }
}
