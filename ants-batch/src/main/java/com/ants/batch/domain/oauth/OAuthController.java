package com.ants.batch.domain.oauth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by betterfly
 * Date : 2019.04.16
 */
@RestController
public class OAuthController {

    @RequestMapping("/callback")
    public String callback() {
        return "/";
    }
}
