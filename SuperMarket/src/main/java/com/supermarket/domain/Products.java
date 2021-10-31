package com.supermarket.domain;

import lombok.Getter;

/**
 * The enum Product code. In the next version of SuperMarket this will be driven from an item table from a database.
 */
public enum Products {
    /**
     * The Gr 1.
     */
    GR1("Green Tea"),
    /**
     * Sr 1 product code.
     */
    SR1("Strawberries"),
    /**
     * Cf 1 product code.
     */
    CF1("Coffee");
    @Getter
    private final String description;

    Products(String description) {
        this.description = description;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return this.toString();
    }
}
