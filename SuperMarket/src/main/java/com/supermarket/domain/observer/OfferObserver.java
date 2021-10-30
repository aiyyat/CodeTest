package com.supermarket.domain.observer;

import com.supermarket.domain.Item;
import com.supermarket.domain.offer.Offer;

import java.util.LinkedList;
import java.util.List;

public class OfferObserver implements Observer {
    List<Offer> offers = new LinkedList<>();

    @Override
    public void notifyOfferObservers(Item item) {

    }

    @Override
    public void subscribe(Offer offer) {
        offers.add(offer);
    }
}
