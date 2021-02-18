package com.ants.batch.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Profile;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by betterfly
 * Date : 2019.04.28
 */

public class CommonUtills {
//    The ApplicationConversionService is now registered by default with the Environment and BeanFactory created by SpringApplication.
//    This allows you to use application converters directly with core Spring Framework items such as the @Value annotation:

    @Value("${my.duration:10s}")
    private Duration duration;

    public static String currentDate(String format){

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }


    public void asdf(){

        ApplicationConversionService service = new ApplicationConversionService();
        String parseString = service.convert(duration, String.class);

    }
}
