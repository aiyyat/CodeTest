package com.supermarket.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.NonFinal;

import java.math.BigDecimal;

/**
 * The type Item.
 */
@AllArgsConstructor
public class Item implements Cloneable {
    @Getter
    @NonFinal
    private ProductCode productCode;
    @Getter
    @NonFinal
    private BigDecimal costInPounds;

    public Item clone() {
        return new Item(productCode, costInPounds);
    }
}
