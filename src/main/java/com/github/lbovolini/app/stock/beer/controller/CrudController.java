package com.github.lbovolini.app.stock.beer.controller;

import java.util.UUID;

public interface CrudController<T, R> {

    R delete(UUID id);

    R find(String name);

    R save(T t);

    R update(T t);
}
