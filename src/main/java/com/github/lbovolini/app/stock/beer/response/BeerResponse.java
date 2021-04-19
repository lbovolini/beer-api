package com.github.lbovolini.app.stock.beer.response;

import com.github.lbovolini.app.stock.beer.constants.BeerType;
import com.github.lbovolini.app.stock.beer.controller.BeerController;
import com.github.lbovolini.app.stock.beer.dto.BeerDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class BeerResponse extends RepresentationModel<EntityModel<BeerDTO>> implements Serializable {

    private UUID id;

    private String name;

    private String brand;

    private Integer max;

    private Integer quantity;

    private BeerType type;

    public BeerResponse() {
        super(linkTo(BeerController.class).withSelfRel());
    }

    public BeerResponse(UUID id, String name, String brand, Integer max, Integer quantity, BeerType type) {
        this();
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

        if (!super.equals(object)) { return false; }

        BeerResponse that = (BeerResponse) object;

        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(brand, that.brand)
                && Objects.equals(max, that.max)
                && Objects.equals(quantity, that.quantity)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, brand, max, quantity, type);
    }
}
