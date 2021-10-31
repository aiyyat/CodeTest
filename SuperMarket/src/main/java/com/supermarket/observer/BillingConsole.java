package com.supermarket.observer;

import com.supermarket.domain.Item;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Billing console. This is the Subject in the Observer.
 */
public class BillingConsole implements Subject {
    private BillingMachine billingMachine;
    private List<Item> items = new LinkedList<>();

    /**
     * Instantiates a new Billing console.
     *
     * @param billingMachine the billing machine
     */
    public BillingConsole(BillingMachine billingMachine) {
        this.billingMachine = billingMachine;
    }

    public void addItem(Item item) {
        this.items.add(item);
        billingMachine.addItem(item);
    }
}
