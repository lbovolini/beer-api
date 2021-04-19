package com.github.lbovolini.app.stock.beer.repository;

import com.github.lbovolini.app.stock.beer.entity.Beer;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BeerRepository {

    Boolean deleteById(UUID id);

    Collection<Beer> findAll();

    Optional<Beer> findById(UUID id);

    Optional<Beer> findByName(String name);

    Beer save(Beer beer);

    Beer update(Beer beer);
}
