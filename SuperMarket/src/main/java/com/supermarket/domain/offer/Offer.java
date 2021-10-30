package com.supermarket.domain.offer;

import com.supermarket.domain.Item;
import com.supermarket.domain.NetBill;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Offer.
 */
public abstract class Offer {
    private Item discountItem;
    @Getter
    private List<Item> items = new LinkedList<>();
    @Getter
    private NetBill netBill;

    public Offer(NetBill netBill) {
        this.netBill = netBill;
    }

    /**
     * Reset after.
     */
    public void resetOffer() {
        throw new UnsupportedOperationException();
    }

    /**
     * Matches boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public abstract Boolean matches(Item item);

    /**
     * Apply.
     *
     * @param item the item
     */
    public abstract void apply(Item item);
}
