package com.supermarket.domain;

import lombok.AccessLevel;
import lombok.Value;
import lombok.With;
import lombok.experimental.NonFinal;

import java.math.BigDecimal;

/**
 * The type Item.
 */
@Value
public class Item implements Cloneable {
    @With(AccessLevel.PACKAGE)
    @NonFinal
    private ProductCode productCode;
    @With(AccessLevel.PACKAGE)
    @NonFinal
    private String description;
    @With(AccessLevel.PACKAGE)
    @NonFinal
    private BigDecimal costInPounds;

    public Item clone() {
        return new Item(productCode, description, costInPounds);
    }
}
