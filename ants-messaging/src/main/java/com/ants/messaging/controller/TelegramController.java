package com.ants.messaging.controller;

import com.ants.messaging.model.TelegramChannelDetail;
import com.ants.messaging.model.TelegramMessageDto;
import com.ants.messaging.service.TelegramService;
import com.ants.library.common.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by betterfly
 * Date : 2019.09.04
 */

@RestController
@RequestMapping("/api/telegram")
@RequiredArgsConstructor
@Slf4j
public class TelegramController {

    private final TelegramService telegramService;

    @GetMapping("/healthCheck")
    public String healthCheck(){
        return "Welcome to ants service!";
    }

    @PostMapping("/{chatId}/send")
    public Response<String> send(
            @PathVariable("chatId") Long chatId,
            @Valid @RequestBody TelegramMessageDto dto) throws IOException {
        log.info("request msg : {}, to {}", dto.getText(), chatId);

        telegramService.sendMessage(
                TelegramMessageDto.builder()
                .chatId(chatId)
                .text(dto.getText())
                .build()
        );

        return Response.ok();
    }

    @GetMapping("/channelInfo")
    public List<TelegramChannelDetail.Chat> getUpdates() throws IOException {
        return telegramService.getChannelInfo();
    }
}