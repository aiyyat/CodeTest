package com.supermarket.domain.offer;

import com.supermarket.domain.Bill;
import com.supermarket.domain.Item;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Buy n get m offer.
 */
public class BuyNGetMPoundOffDiscountOffer extends Offer {
    private final String description;
    private Item onesLikeThis;
    private Integer n = 0;
    private BigDecimal m;
    private List<Item> items = new LinkedList<>();

    public BuyNGetMPoundOffDiscountOffer(String description, Item forItem, Integer n, BigDecimal m) {
        this.description = description;
        this.onesLikeThis = forItem;
        this.n = n;
        this.m = m;
    }

    @Override
    public Boolean matches(Item item) {
        return item.getProductCode() == this.onesLikeThis.getProductCode();
    }

    @Override
    public void apply(Bill bill, Item item) {
        if (matches(item)) {
            items.add(item);
            if (items.size() == n) {
                final BigDecimal amount = m.multiply(new BigDecimal(n));
                bill.applyDiscount(description + " - Offer Kicks in!", amount);
            } else if (items.size() > n) {
                bill.applyDiscount(description, m);
            }
        }
    }
}
