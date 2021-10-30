package com.supermarket.domain.observer;

import com.supermarket.domain.Item;
import com.supermarket.domain.offer.Offer;

public interface Observer {
    public void notifyOfferObservers(Item item);

    public void subscribe(Offer offer);
}
