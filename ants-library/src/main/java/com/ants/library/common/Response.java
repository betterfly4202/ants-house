package com.ants.library.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.ants.library.exception.StatusCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final StatusCode code;
    private final String message;
    private final T data;

    private Response(@NonNull StatusCode code, @NonNull String message, @Nullable T data) {
        this.code = code;
        this.message = Optional.of(message).orElse(code.getMessage());
        this.data = data;
    }

    public static <T> Response<T> ok(@Nullable T data) {
        return new Response<>(StatusCode.OK, StatusCode.OK.getMessage(), data);
    }

    public static Response<String> ok() {
        return new Response<>(StatusCode.OK, StatusCode.OK.getMessage(), "success");
    }

    public static Response fail(@NonNull String message) {
        return new Response<>(StatusCode.FAIL, message, null);
    }

    public static <T> Response fail(@NonNull String message, @Nullable T data) {
        return new Response<>(StatusCode.FAIL, message, data);
    }
}
