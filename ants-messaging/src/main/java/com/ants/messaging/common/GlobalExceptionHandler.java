package com.ants.messaging.common;

import com.ants.library.common.Response;
import com.ants.library.common.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> handleValidException(Exception e) {
        log.error("{}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Response.fail(
                        log.isDebugEnabled()
                                ? String.join(" ",
                                e.getClass().getSimpleName(),
                                Optional.ofNullable(e.getMessage()).orElse(e.toString()))
                                : "we can not process your request. Please contact your administrator."));
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<Response> handleBindException(BindException e) {
        log.error("handleBindException", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Response.fail("validation error", simplify(e.getBindingResult())));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Response> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Response.fail("validation error", simplify(e.getBindingResult())));
    }

    private static List<ValidationError> simplify(BindingResult bindingResult){
        return bindingResult.getFieldErrors()
                .stream()
                .map(err -> ValidationError.builder()
                        .field(err.getField())
                        .value(err.getRejectedValue())
                        .reason(err.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
