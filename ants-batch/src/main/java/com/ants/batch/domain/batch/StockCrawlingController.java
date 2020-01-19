package com.ants.batch.domain.batch;

import com.google.api.client.json.Json;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by betterfly
 * Date : 2019.04.17
 */

@RestController
@RequestMapping("/api/batch")
@Slf4j
public class StockCrawlingController {
    @Autowired
    private StockMarketCommand stockMarketCommand;

    @Autowired
    private KosdaqMarketCommand kosdaqMarketCommand;

    @Autowired
    private KonexMarketCommand konexMarketCommand;

    @GetMapping("/stockMkt")
    public ResponseEntity<String> getStockList(){
        stockMarketCommand.execute();

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/kosdaqMkt")
    public ResponseEntity<String> getKosdaqList(){
        kosdaqMarketCommand.execute();

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/konexMkt")
    public ResponseEntity<String> getKonexList(){
        konexMarketCommand.execute();

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
