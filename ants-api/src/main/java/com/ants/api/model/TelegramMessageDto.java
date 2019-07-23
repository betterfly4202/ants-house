package com.ants.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by betterfly
 * Date : 2019.09.04
 */

@Getter
@NoArgsConstructor
@ToString
public class TelegramMessageDto implements Serializable {
    @JsonProperty("chat_id")
    @Setter
    private Long chatId;
    @JsonProperty("text")
    private String text;

    @Builder
    public TelegramMessageDto(@org.springframework.lang.NonNull Long chatId, @Nullable String text) {
        this.chatId = chatId;
        this.text = Optional.ofNullable(text).map(Object::toString).orElse("");
    }
}
