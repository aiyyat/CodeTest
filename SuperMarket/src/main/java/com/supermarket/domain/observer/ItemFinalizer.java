package com.supermarket.domain.observer;

import com.supermarket.domain.Item;

import java.util.LinkedList;
import java.util.List;

public class ItemFinalizer implements Subject {
    private BillGenerator billGenerator;
    private List<Item> items = new LinkedList<>();

    public ItemFinalizer(BillGenerator billGenerator) {
        this.billGenerator = billGenerator;
    }

    public void addItem(Item item) {
        this.items.add(item);
        billGenerator.addItem(item);
    }
}
