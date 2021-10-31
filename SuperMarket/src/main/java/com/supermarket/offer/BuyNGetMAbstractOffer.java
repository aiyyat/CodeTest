package com.supermarket.offer;

import com.supermarket.domain.Bill;
import com.supermarket.domain.Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Buy n get m offer. e.g. buy 1 get 1 free.
 */
public class BuyNGetMAbstractOffer extends AbstractOffer {
    private Item itemsLikeThisOne;
    private Integer n = 0;
    private Integer m = 0;
    private List<Item> items = new LinkedList<>();

    /**
     * Instantiates a new Buy n get m offer.
     *
     * @param forItem the for item
     * @param n       the n
     * @param m       the m
     */
    public BuyNGetMAbstractOffer(Item forItem, Integer n, Integer m) {
        this.itemsLikeThisOne = forItem;
        this.n = n;
        this.m = m;
    }

    @Override
    public void resetOffer() {
        this.items = new LinkedList<>();
    }

    @Override
    public Boolean matches(Item item) {
        return item.getProductsCode() == this.itemsLikeThisOne.getProductsCode();
    }

    @Override
    public void apply(Bill bill, Item item) {
        if (matches(item)) {
            items.add(item);
            if (items.size() == n) {
                List<Item> freebies = new ArrayList<>();
                for (int i = 0; i < m; i++) {
                    freebies.add(itemsLikeThisOne.clone());
                }
                bill.addFreebies(freebies);
                resetOffer();
            }
        }
    }
}
