package com.github.lbovolini.app.stock.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyRegisteredException extends RuntimeException {

    public ResourceAlreadyRegisteredException(String message) {
        super(message);
    }
}
