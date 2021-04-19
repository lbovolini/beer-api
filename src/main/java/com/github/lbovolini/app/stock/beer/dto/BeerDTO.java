package com.github.lbovolini.app.stock.beer.dto;

import com.github.lbovolini.app.stock.beer.constants.BeerType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class BeerDTO implements Serializable {

    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String brand;

    @NotNull
    @Max(500)
    private Integer max;

    @NotNull
    @Max(100)
    private Integer quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BeerType type;

    public BeerDTO() {
    }

    public BeerDTO(UUID id, String name, String brand, Integer max, Integer quantity, BeerType type) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.max = max;
        this.quantity = quantity;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BeerType getType() {
        return type;
    }

    public void setType(BeerType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) { return true; }

        if (object == null || getClass() != object.getClass()) { return false; }

        BeerDTO beerDTO = (BeerDTO) object;

        return Objects.equals(id, beerDTO.id)
                && Objects.equals(name, beerDTO.name)
                && Objects.equals(brand, beerDTO.brand)
                && Objects.equals(max, beerDTO.max)
                && Objects.equals(quantity, beerDTO.quantity)
                && type == beerDTO.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, max, quantity, type);
    }
}
