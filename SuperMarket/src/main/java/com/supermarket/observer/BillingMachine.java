package com.supermarket.observer;

import com.supermarket.domain.Bill;
import com.supermarket.domain.Item;
import com.supermarket.offer.Offer;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Billing machine. This is the Oberver and lets Offers Subscribe to it.
 */
public class BillingMachine implements Observer {
    private List<Offer> offers = new LinkedList<>();
    private Bill bill;

    /**
     * Instantiates a new Billing machine.
     *
     * @param bill the net bill
     */
    public BillingMachine(Bill bill) {
        this.bill = bill;
    }

    @Override
    public void addItem(Item item) {
        bill.addItem(item);
        offers.forEach(offer -> {
            if (offer.matches(item)) {
                offer.apply(bill, item);
            }
        });
    }

    @Override
    public void subscribe(Offer offer) {
        offers.add(offer);
    }
}
