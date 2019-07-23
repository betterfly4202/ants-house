package com.ants.library.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Optional;

@ToString
@Getter
public class ValidationError implements Serializable {
    private String field;
    private String value;
    private String reason;

    @Builder
    public ValidationError(@NonNull String field, @Nullable Object value, @NonNull String reason) {
        this.field = field;
        this.value = Optional.ofNullable(value).map(Object::toString).orElse("");
        this.reason = reason;
    }
}
