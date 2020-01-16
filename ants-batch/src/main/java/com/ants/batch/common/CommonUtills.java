package com.ants.batch.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by betterfly
 * Date : 2019.04.28
 */
public class CommonUtills {
    public static String currentDate(String format){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }
}
