package com.supermarket.domain.observer;

import com.supermarket.domain.Item;
import com.supermarket.domain.NetBill;
import com.supermarket.domain.offer.Offer;

import java.util.LinkedList;
import java.util.List;

public class BillGenerator implements Observer {
    private List<Offer> offers = new LinkedList<>();
    private NetBill netBill;

    public BillGenerator(NetBill netBill) {
        this.netBill = netBill;
    }

    @Override
    public void addItem(Item item) {
        netBill.addItem(item);
        offers.forEach(offer -> {
            if (offer.matches(item)) {
                offer.apply(netBill, item);
            }
        });
    }

    @Override
    public void subscribe(Offer offer) {
        offers.add(offer);
    }
}
