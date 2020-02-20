package com.ants.batch.domain.sheets;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.ants.batch.common.CommonUtills;
import com.ants.batch.common.GoogleConnector;
import com.ants.batch.common.SheetsConstants;
import com.ants.batch.domain.stockinfo.StockInfo;
import com.ants.batch.domain.stockinfo.StockInfoRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by betterfly
 * Date : 2019.04.06
 */

@Slf4j
@Service
public class SheetServiceImpl{

    @Autowired
    private GoogleConnector googleConnector;

    @Autowired
    private StockInfoRepositoryImpl stockInfoService;

    private Sheets sheetsService;

    @Value("${google.sheet.id}")
    private String sheetId;

    public SheetServiceImpl() {
        googleConnector= new GoogleConnector();
        sheetsService = googleConnector.getSheets();
    }

    public void makeSpreadSheet(String sheetTitle) throws IOException {
        Spreadsheet spreadsheet =
                new Spreadsheet()
                        .setProperties(new SpreadsheetProperties()
                        .setTitle(CommonUtills.currentDate("yyMMdd")+"_"+sheetTitle));

        spreadsheet =
                sheetsService.spreadsheets()
                             .create(spreadsheet)
                             .execute();

        log.info("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
    }

    public List<List<Object>> readSheetValues(String sheetId, String range) throws IOException {
        ValueRange sheetValues =
                sheetsService.spreadsheets()
                             .values()
                             .get(sheetId, range)
                             .execute();

        return sheetValues.getValues();
    }

    public List<List<Object>> readSheetValuess(String sheetId, String range) throws IOException {
        Sheets.Spreadsheets.Get request = sheetsService.spreadsheets().get(sheetId);
        request.setRanges(Arrays.asList("A1:B5"));
        request.setIncludeGridData(true);

        Spreadsheet response = request.execute();

        return null;
    }

    public void aa(String sheetId, String range) throws IOException {
        Sheets.Spreadsheets.Values.BatchGet request =
                sheetsService.spreadsheets().values().batchGet(sheetId);
        request.setRanges(Arrays.asList(range));
        request.setValueRenderOption("FORMATTED_VALUE");

        BatchGetValuesResponse response = request.execute();
        response.getValueRanges();
    }

    public void sheetUpdates(String spreadCommand) throws IOException {
        Sheets sheetsService = googleConnector.getSheets();
        Sheets.Spreadsheets.Values.Update request =
                sheetsService.spreadsheets()
                             .values()
                             .update(sheetId, "A1:B9", setSpreadCommand(spreadCommand));

        request.setValueInputOption(SheetsConstants.VALUE_INPUT_OPTIONS);

        UpdateValuesResponse response = request.execute();
        response.getUpdatedRange();
        response.getUpdatedData();
        response.getUpdatedRows();
        response.values();

        System.out.println(response);
    }

    private ValueRange setSpreadCommand(String spreadCommand){
        List<List<Object>> list = new ArrayList<>();
        List<Object> subList = new ArrayList<>();
        subList.add(spreadCommand);
        list.add(subList);

        return new ValueRange().setMajorDimension(SheetsConstants.MAJOR_DIMENSION)
                               .setValues(list);
    }

    private String makeSpreadCommand(StockInfo stockInfo){
        final StockInfo dto = stockInfoService.findByStockInfoQueryAdvance(stockInfo.getCode(), stockInfo.getCompany());

        return FinanceCommandMaker.builder()
                   .stockSubject(new FinanceCommandMaker().parseToStockSubject(dto))
                   .attributes("price")
                   .startDate("TODAY()-30")
                   .endDate("7")
                   .build()
            .getCommand();
    }

    public List<List<Object>> getFinanceDetail(StockInfo stockInfo){
        List<List<Object>> sheetValues = null;
        try {
            sheetUpdates(makeSpreadCommand(stockInfo));
            sheetValues = readSheetValuess(sheetId, "A1:B5");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sheetValues;
    }
}