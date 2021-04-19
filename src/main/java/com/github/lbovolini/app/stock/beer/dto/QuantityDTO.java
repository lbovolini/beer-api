package com.github.lbovolini.app.stock.beer.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class QuantityDTO implements Serializable {

    @NotNull
    @Max(100)
    private Integer quantity;

    public QuantityDTO() {
    }

    public QuantityDTO(@NotNull @Max(100) Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
