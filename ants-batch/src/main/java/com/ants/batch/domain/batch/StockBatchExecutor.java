package com.ants.batch.domain.batch;

import com.ants.batch.domain.stockinfo.StockInfo;
import com.ants.batch.domain.stockinfo.StockInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by betterfly
 * Date : 2019.04.20
 */

@Slf4j
@AllArgsConstructor
@Component
public class StockBatchExecutor {

    @Autowired
    private StockInfoRepository stockInfoRepository;

    final String baseUrl = "https://kind.krx.co.kr/corpgeneral/corpList.do";
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private MarketType marketType;

    public StockBatchExecutor(){
        restTemplate = new RestTemplate();
        headers = setHeaders();
    }

    private HttpHeaders setHeaders(){
        headers = new HttpHeaders();

        List<Locale.LanguageRange> langList =
                Arrays.asList(
                        new Locale.LanguageRange("ko-KR")
                      , new Locale.LanguageRange("ko")
                      , new Locale.LanguageRange("en-US"));

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAcceptLanguage(langList);

        return headers;
    }

    public void executeStockMarket(MarketType marketType) {
        this.marketType = marketType;

        getStockElementList()
                .stream()
                .map(m -> m.getElementsByTag("td").eachText())
                .filter(v -> v.size() == 10)
                .forEach(v -> {
                    if(v.iterator().hasNext()) {
                        saveStockService(v);
                    }
                });
    }

    private void saveStockService(List<String> stockElementList){
        stockInfoRepository
                .save(StockInfo.builder()
                    .type(marketType.getType())
                    .company(stockElementList.get(1))
                    .code((stockElementList.get(2)))
                    .businessType(stockElementList.get(3))
                    .mainProduct(stockElementList.get(4))
                    .listedDate(stockElementList.get(5))
                    .settlingMonth(stockElementList.get(6))
                    .president(stockElementList.get(7))
                    .homepage(stockElementList.get(8))
                    .area(stockElementList.get(9))
                .build());
    }

    private List<Element> getStockElementList(){
        MultiValueMap<String, String> map = setStockFormParameter();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request , String.class);
        Document doc = Jsoup.parse(Objects.requireNonNull(response.getBody()));

        return doc.body().getElementsByTag("tr").tagName("td");
    }

    private MultiValueMap<String, String> setStockFormParameter(){
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("method", "download");
        map.add("marketType", marketType.getStockName());
        map.add("pageIndex", "1");
        map.add("orderMode", "3");
        map.add("orderStat", "D");
        map.add("searchType", "13");
        map.add("fiscalYearEnd", "all");
        map.add("location", "all");

        return map;
    }
}
