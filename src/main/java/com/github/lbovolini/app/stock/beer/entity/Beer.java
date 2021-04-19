package com.github.lbovolini.app.stock.beer.entity;

import com.github.lbovolini.app.stock.beer.constants.BeerType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Beer {

    @Id
    @Type(type="uuid-binary")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @NotNull
    private Integer max;

    @NotNull
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private BeerType type;

    public Beer() {
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

        Beer that = (Beer) object;

        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(brand, that.brand)
                && Objects.equals(max, that.max)
                && Objects.equals(quantity, that.quantity)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, max, quantity, type);
    }
}
