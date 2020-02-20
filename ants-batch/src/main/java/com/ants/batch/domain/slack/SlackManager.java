package com.ants.batch.domain.slack;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by betterfly
 * Date : 2019.07.10
 */

@Slf4j
@Component
public class SlackManager {

    @Autowired
    private RestTemplate restTemplate;

    public enum SlackTarget {
        INCOMING_URL("https://hooks.slack.com/services/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", "incoming");

        String webHookUrl;
        String channel;

        SlackTarget(String webHookUrl, String channel) {
            this.webHookUrl = webHookUrl;
            this.channel = channel;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SlackMessageAttachement {
        private String channel;
        private String username;
        private String icon_emoji;
        private String text;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SlackMessage {
        private String text;
        private String channel;
        private List<SlackMessageAttachement> attachments;

        void addAttachment(SlackMessageAttachement attachement) {
            if (this.attachments == null) {
                this.attachments = Lists.newArrayList();
            }

            this.attachments.add(attachement);
        }
    }

    public boolean notify(SlackTarget target, SlackMessageAttachement message) {
        log.debug("Notify[target: {}, message: {}]", target, message);

        SlackMessage slackMessage =
                SlackMessage.builder()
                        .channel(target.channel)
                        .attachments(Lists.newArrayList(message))
                        .build();

        try {
            restTemplate.postForEntity(target.webHookUrl, slackMessage, String.class);
            return true;
        } catch (Exception e) {
            log.error("Occur Exception: {}", e); return false;
        }
    }
}
