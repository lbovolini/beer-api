package com.github.lbovolini.app.stock.beer.service;

public interface CrudService<R, P, ID> {

    void deleteById(ID id);

    R findById(ID id);

    R save(P p);

    R update(P p);
}
