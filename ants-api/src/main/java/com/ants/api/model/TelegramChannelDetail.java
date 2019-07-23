package com.ants.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by betterfly
 * Date : 2019.09.04
 */

@Getter
@NoArgsConstructor
@ToString
public class TelegramChannelDetail implements Serializable {
    @JsonProperty("ok")
    private Boolean isSuccess;

    @JsonProperty("result")
    private List<Result> result;

    @Getter
    private static class Result {
        @JsonProperty("update_id")
        private Integer updateId;
        @JsonProperty("channel_post")
        private Map<String, Object> channelPost;
    }

    @Getter
    public static class Chat{
        private Long id;
        private String title;
        private String type;
    }

    public static List<Chat> getChannelDetail(String input) throws IOException {
        return new ArrayList<>(new ObjectMapper().readValue(input, TelegramChannelDetail.class)
                .getResult())
                .stream()
                .map(TelegramChannelDetail.Result::getChannelPost)
                .map(x -> x.get("chat"))
                .map(m -> new ObjectMapper().convertValue(m, TelegramChannelDetail.Chat.class))
                .collect(Collectors.toList());
    }
}
