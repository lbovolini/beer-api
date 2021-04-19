package com.github.lbovolini.app.stock.beer.builder;

import com.github.lbovolini.app.stock.beer.constants.BeerType;
import com.github.lbovolini.app.stock.beer.dto.BeerDTO;

import java.util.UUID;

public class BeerDTOBuilder {

    private UUID id = UUID.fromString("fbcc42ec-05bb-40a0-b2af-96e7d893249e");

    private String name = "Brahma";

    private String brand = "Ambev";

    private int max = 50;

    private int quantity = 10;

    private BeerType type = BeerType.LAGER;

    public BeerDTO toBeerDTO() {
        return new BeerDTO(id,
                name,
                brand,
                max,
                quantity,
                type);
    }
}
