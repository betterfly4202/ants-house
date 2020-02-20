package com.ants.batch.domain.sheets;

import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;

/**
 * Created by betterfly
 * Date : 2019.04.28
 */
public interface SheetService {
    void makeSpreadSheet(String sheetTitle) throws IOException;
    ValueRange readSheets(String range) throws IOException;
}
