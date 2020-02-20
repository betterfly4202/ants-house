package com.ants.batch.domain.slack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by betterfly
 * Date : 2019.07.10
 */

@RestController
@RequestMapping("/api/alert")
public class SlackController {

    @Autowired
    private SlackManager slackManager;

    @PostMapping("/slack")
    public ResponseEntity<Boolean> send(@RequestBody SlackManager.SlackMessageAttachement message) {
        return ResponseEntity.ok(slackManager.notify(SlackManager.SlackTarget.INCOMING_URL, message));
    }
}
