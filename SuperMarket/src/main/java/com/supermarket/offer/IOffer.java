package com.supermarket.offer;

import com.supermarket.domain.Bill;
import com.supermarket.domain.Item;

/**
 * The type Offer. This simulates an Item Observer.
 * It listens to add items to bill events if it subscribes to the event and
 * also matches the offers demands to consider the item for an offer.
 */
public interface IOffer {
    /**
     * Reset offer.
     */
    public void resetOffer();

    /**
     * Matches boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public Boolean matches(Item item);

    /**
     * Apply.
     *
     * @param bill the bill
     * @param item the item
     */
    public void apply(Bill bill, Item item);
}
