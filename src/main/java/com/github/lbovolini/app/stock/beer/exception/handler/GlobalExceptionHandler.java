package com.github.lbovolini.app.stock.beer.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lbovolini.app.stock.beer.exception.ResourceAlreadyRegisteredException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

//@RestControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    static class ApiError {
        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;
        private final List<Error> errors;

        private ApiError(LocalDateTime timestamp, int status, String error, String message, String path, List<Error> errors) {
            super();
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
            this.path = path;
            this.errors = errors;
        }

        public static ApiError with(HttpStatus httpStatus, String message, String path) {
            return new ApiError(
                    LocalDateTime.now(ZoneId.of("UTC")),
                    httpStatus.value(),
                    httpStatus.getReasonPhrase(),
                    message,
                    path,
                    List.of());
        }

        public static ApiError with(HttpStatus httpStatus, String message, String path, List<Error> errors) {
            return new ApiError(
                    LocalDateTime.now(ZoneId.of("UTC")),
                    httpStatus.value(),
                    httpStatus.getReasonPhrase(),
                    message,
                    path,
                    errors);
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public List<Error> getErrors() {
            return errors;
        }
    }

    static class Error {
        private final String field;
        private final String message;
        private final String value;

        public Error(String field, String message, String value) {
            this.field = field;
            this.message = message;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }

        public String getValue() {
            return value;
        }
    }

    @ExceptionHandler(ResourceAlreadyRegisteredException.class)
    public void handleResourceAlreadyRegisteredException() {

    }
}
