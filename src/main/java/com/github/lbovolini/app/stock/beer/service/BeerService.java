package com.github.lbovolini.app.stock.beer.service;

import com.github.lbovolini.app.stock.beer.dto.BeerDTO;

import java.util.List;
import java.util.UUID;

public interface BeerService extends CrudService<BeerDTO, BeerDTO, UUID> {

    List<BeerDTO> findAll();

    BeerDTO findByName(String name);

    BeerDTO decrement(UUID id, int quantity);

    BeerDTO increment(UUID id, int quantity);
}
