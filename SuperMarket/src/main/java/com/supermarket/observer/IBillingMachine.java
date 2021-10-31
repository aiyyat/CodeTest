package com.supermarket.observer;

import com.supermarket.domain.Item;
import com.supermarket.offer.AbstractOffer;

/**
 * The interface Observer. Could have use the java Obeservable but did this as a chance to
 * demonstrate the internals of a simple Observables.
 */
public interface IBillingMachine {
    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(Item item);

    /**
     * Subscribe.
     *
     * @param offer the offer
     */
    public void subscribe(AbstractOffer offer);
}
