package com.supermarket.domain.observer;

import com.supermarket.domain.Item;
import com.supermarket.domain.offer.Offer;

/**
 * The interface Observer. Could have use the java Obeservable but did this as a chance to
 * demonstrate the internals of a simple Observables.
 */
public interface Observer {
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
    public void subscribe(Offer offer);
}
