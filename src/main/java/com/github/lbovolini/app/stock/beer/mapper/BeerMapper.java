package com.github.lbovolini.app.stock.beer.mapper;

import com.github.lbovolini.app.stock.beer.dto.BeerDTO;
import com.github.lbovolini.app.stock.beer.entity.Beer;
import com.github.lbovolini.app.stock.beer.response.BeerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    BeerDTO toDTO(Beer beer);

    Beer toEntity(BeerDTO beerDTO);

    BeerResponse toResponse(BeerDTO beerDTO);
}
