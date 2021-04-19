package com.github.lbovolini.app.stock.beer.service;

import com.github.lbovolini.app.stock.beer.dto.BeerDTO;
import com.github.lbovolini.app.stock.beer.entity.Beer;
import com.github.lbovolini.app.stock.beer.exception.ResourceAlreadyRegisteredException;
import com.github.lbovolini.app.stock.beer.exception.ResourceNotFoundException;
import com.github.lbovolini.app.stock.beer.exception.ResourceStockExceededException;
import com.github.lbovolini.app.stock.beer.mapper.BeerMapper;
import com.github.lbovolini.app.stock.beer.repository.BeerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public BeerDTO decrement(UUID id, int quantity) {
        Beer beerToDecrementStock = findByIdOrFail(id);

        int quantityAfterIncrement = beerToDecrementStock.getQuantity() - quantity;
        if (quantityAfterIncrement < 0) {
            throw new ResourceStockExceededException(String.format("Beer with id: %s to decrement informed exceeds the minimum stock capacity: 0", id));
        }

        beerToDecrementStock.setQuantity(quantityAfterIncrement);
        Beer decrementedBeerStock = beerRepository.save(beerToDecrementStock);

        return beerMapper.toDTO(decrementedBeerStock);
    }

    @Override
    public void deleteById(UUID id) {
        boolean found = beerRepository.deleteById(id);

        if (!found) {
            throw new ResourceNotFoundException(String.format("Beer with id: %s not found", id));
        }
    }

    @Override
    public List<BeerDTO> findAll() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDTO findById(UUID id) {
        return beerRepository.findById(id)
                .map(beerMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Beer with id: %s not found", id)));
    }

    @Override
    public BeerDTO findByName(String name) {
        return beerRepository.findByName(name)
                .map(beerMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Beer with name: %s not found", name)));
    }

    @Override
    public BeerDTO save(BeerDTO beerDTO) {
        failIfBeerExists(beerDTO.getName());

        Beer beer = beerMapper.toEntity(beerDTO);
        Beer savedBeer = beerRepository.save(beer);

        return beerMapper.toDTO(savedBeer);
    }

    @Override
    public BeerDTO update(BeerDTO beerDTO) {
        Beer beer = beerMapper.toEntity(beerDTO);
        Beer updatedBeer = beerRepository.save(beer);

        return beerMapper.toDTO(updatedBeer);
    }

    public BeerDTO increment(UUID id, int quantity) {
        Beer beerToIncrementStock = findByIdOrFail(id);

        int quantityAfterIncrement = quantity + beerToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantity);
            Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);

            return beerMapper.toDTO(incrementedBeerStock);
        }
        throw new ResourceStockExceededException(String.format("Beer with id: %s to increment informed exceeds the maximum stock capacity: %s", id, beerToIncrementStock.getMax()));
    }

    private void failIfBeerExists(String name) {
        beerRepository.findByName(name).ifPresent(beer -> {
            throw new ResourceAlreadyRegisteredException(String.format("Beer with name: %s already registered", name));
        });
    }

    private Beer findByIdOrFail(UUID id) {
        return beerRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(String.format("Beer with id: %s already registered", id));
        });
    }
}
