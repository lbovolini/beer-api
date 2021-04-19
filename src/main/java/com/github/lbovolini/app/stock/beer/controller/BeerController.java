package com.github.lbovolini.app.stock.beer.controller;

import com.github.lbovolini.app.stock.beer.dto.BeerDTO;
import com.github.lbovolini.app.stock.beer.dto.QuantityDTO;
import com.github.lbovolini.app.stock.beer.mapper.BeerMapper;
import com.github.lbovolini.app.stock.beer.response.BeerResponse;
import com.github.lbovolini.app.stock.beer.service.BeerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/beers")
public class BeerController implements CrudController<BeerDTO, HttpEntity<?>> {

    private final BeerService beerService;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @PatchMapping("{id}/decrement")
    public HttpEntity<BeerResponse> decrement(@Valid @NotNull @PathVariable UUID id, @Valid @RequestBody QuantityDTO quantityDTO) {
        BeerDTO beerDTO = beerService.decrement(id, quantityDTO.getQuantity());

        return ResponseEntity.ok(beerMapper.toResponse(beerDTO));
    }

    @DeleteMapping("{id}")
    @Override
    public HttpEntity<Void> delete(@Valid @NotNull @PathVariable UUID id) {
        beerService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{name}")
    @Override
    public HttpEntity<BeerResponse> find(@Valid @NotNull @PathVariable String name) {
        BeerDTO beerDTO = beerService.findByName(name);

        return ResponseEntity.ok(beerMapper.toResponse(beerDTO));
    }

    // !TODO
    @GetMapping
    public HttpEntity<List<BeerResponse>> findAll() {
        List<BeerDTO> beerResponseList = beerService.findAll();

        return ResponseEntity.ok(beerResponseList.stream().map(beerMapper::toResponse).collect(Collectors.toList()));
    }

    @PatchMapping("{id}/increment")
    public HttpEntity<BeerResponse> increment(@Valid @NotNull @PathVariable UUID id, @Valid @RequestBody QuantityDTO quantityDTO) {
        BeerDTO beerDTO = beerService.increment(id, quantityDTO.getQuantity());

        return ResponseEntity.ok(beerMapper.toResponse(beerDTO));
    }


    @PostMapping
    @Override
    public HttpEntity<BeerResponse> save(@Valid @RequestBody BeerDTO beerDTO) {
        BeerDTO savedBeerDTO = beerService.save(beerDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(savedBeerDTO.getId() + "/")
                .build()
                .toUri();

        return ResponseEntity.created(location).body(beerMapper.toResponse(savedBeerDTO));
    }

    @PutMapping
    @Override
    public HttpEntity<BeerResponse> update(@Valid @RequestBody BeerDTO beerDTO) {
        BeerDTO updatedBeerDTO = beerService.update(beerDTO);

        return ResponseEntity.ok(beerMapper.toResponse(updatedBeerDTO));
    }
}
