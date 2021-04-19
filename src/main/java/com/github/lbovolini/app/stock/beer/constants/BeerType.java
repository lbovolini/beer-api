package com.github.lbovolini.app.stock.beer.constants;

public enum BeerType {

    LAGER("Lager"),
    MALZBIER("Malzbier"),
    WITBIER("Witbier"),
    WEISS("Weiss"),
    ALE("Ale"),
    IPA("IPA"),
    STOUT("Stout");

    private final String description;

    BeerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
