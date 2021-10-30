package com.supermarket.domain.offer;

import com.supermarket.domain.Item;
import com.supermarket.domain.NetBill;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Buy n get m offer.
 */
public class BuyNGetMPercentOffDiscountOffer extends Offer {
    private final String description;
    private final Item onesLikeThis;
    private final Integer n;
    private final BigDecimal m;
    private BigDecimal amount;
    private List<Item> items = new LinkedList<>();

    public BuyNGetMPercentOffDiscountOffer(String description, Item forItem, Integer n, BigDecimal m) {
        this.description = description;
        this.onesLikeThis = forItem;
        this.n = n;
        this.m = m;
        amount = onesLikeThis.getCostInPounds().multiply(m);
    }

    @Override
    public Boolean matches(Item item) {
        return item.getProductCode() == this.onesLikeThis.getProductCode();
    }

    @Override
    public void apply(NetBill netBill, Item item) {
        if (matches(item)) {
            items.add(item);
            if (items.size() == n) {
                netBill.applyDiscount(description + " - Offer Kicks in!", amount.multiply(new BigDecimal(n)));
            } else if (items.size() > n) {
                netBill.applyDiscount(description, amount);
            }
        }
    }
}
