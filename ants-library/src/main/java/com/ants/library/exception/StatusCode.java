package com.ants.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    OK("success"),
    FAIL("error"),
    ;

    private String message;
}
