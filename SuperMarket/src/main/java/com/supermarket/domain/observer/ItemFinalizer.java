package com.supermarket.domain.observer;

import com.supermarket.domain.Item;

import java.util.LinkedList;
import java.util.List;

public class ItemFinalizer implements Subject {
    private OfferObserver offerObserver = new OfferObserver();
    private List<Item> items = new LinkedList<>();

    public void addItem(Item item) {
        this.items.add(item);
        offerObserver.notifyOfferObservers(item);
    }
}
