package com.supermarket.observer;

import com.supermarket.domain.Bill;
import com.supermarket.domain.Item;
import com.supermarket.offer.AbstractOffer;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Billing machine. This is the Subject in the Observer,
 * this could be easily replaced by Java Observer pattern,
 * however used this opportunity to demonstrate a simple observer implementation.
 */
public class BillingMachine implements IBillingMachine {
    private List<AbstractOffer> offers = new LinkedList<>();
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
    public void subscribe(AbstractOffer offer) {
        offers.add(offer);
    }
}
