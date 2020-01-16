package com.ants.batch.domain.batch;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by betterfly
 * Date : 2019.04.17
 */

public interface Command {
    void execute();
}
