package com.ants.messaging.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by betterfly
 * Date : 2019.09.04
 * Telegram Api : https://core.telegram.org/bots/api
 */

@AllArgsConstructor
@Getter
public enum TelegramApi {
    SEND_MSG("https://api.telegram.org/bot%s/sendMessage"),
    CHANNEL_UPDATES("https://api.telegram.org/bot%s/getUpdates")
    ;

    private String endPoint;
}
