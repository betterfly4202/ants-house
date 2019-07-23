package com.ants.api.service;

import com.ants.api.common.RestApiClient;
import com.ants.api.model.TelegramChannelDetail;
import com.ants.api.model.TelegramMessageDto;
import com.ants.api.type.TelegramApi;
import com.ants.library.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by betterfly
 * Date : 2019.09.11
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramService {
    private final RestApiClient apiClient;

    @Value("${telegram-token}")
    private String telegramToken;

    public HttpResponse sendMessage(TelegramMessageDto dto) throws IOException {
        String endpoint = String.format(TelegramApi.SEND_MSG.getEndPoint(), telegramToken);

        return apiClient.postClient(endpoint, JsonUtils.toJson(dto));
    }

    public List<TelegramChannelDetail.Chat> getChannelInfo() throws IOException {
        return TelegramChannelDetail.getChannelDetail
                (EntityUtils.toString
                        (apiClient.getClient
                                (String.format(TelegramApi.CHANNEL_UPDATES.getEndPoint(), telegramToken)).getEntity(), "UTF-8")
        );
    }
}
