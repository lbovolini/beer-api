package com.github.lbovolini.app.stock.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceStockExceededException extends RuntimeException {

    public ResourceStockExceededException(String message) {
        super(message);
    }
}
