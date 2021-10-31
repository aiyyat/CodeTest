package com.supermarket.domain;

import com.supermarket.exception.BillNotFinalizedException;
import com.supermarket.exception.FinalizedBillModificationException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.supermarket.constants.SuperMarketConstants.STERLING;
import static com.supermarket.util.PriceUtil.priceFormat;

/**
 * The type Bill. This follows Domain Driven Design or the rich model as against anemic model.
 */
public class Bill {
    private final StringBuilder lineItems = new StringBuilder("Welcome to 'A Small Chain Of \uD83D\uDED2 SuperMarket'");
    @Getter
    private List<Item> items = new ArrayList<>();
    @Getter
    private BigDecimal netDiscountInPounds = new BigDecimal("0");
    @Getter
    private BigDecimal netCostInPounds = new BigDecimal("0");
    private BigDecimal customerPayAmountInPounds = null;
    private int itemNumber = 0;
    // Just to demonstrate Exceptions.
    private Boolean finalized = false;

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(Item item) {
        if (!finalized) {
            items.add(item);
            netCostInPounds = netCostInPounds.add(item.getCostInPounds());
            lineItems.append(itemLineItem("Pay", item));
        }
    }

    /**
     * Apply discount.
     *
     * @param description      the description
     * @param discountInPounds the discount in pounds
     */
    public void applyDiscount(String description, BigDecimal discountInPounds) {
        if (!finalized) {
            netDiscountInPounds = netDiscountInPounds.add(discountInPounds);
            lineItems.append(" ").append(description).append(" -").append(STERLING).append(priceFormat(discountInPounds));
        }
    }

    /**
     * Add freebies anytime at the cashier's responsibility to make unhappy customers happy.
     *
     * @param freebies the freebies
     */
    public void addFreebies(List<Item> freebies) {
        freebies.forEach(item -> {
            lineItems.append(itemLineItem("Free!", item));
        });
    }

    /**
     * Finalize bill string.
     *
     * @return the string
     */
    public void finalizeBill() {
        if (!finalized) {
            finalized = true;
            customerPayAmountInPounds = netCostInPounds.subtract(netDiscountInPounds);
            lineItems
                    .append("\nYou only pay: ")
                    .append(STERLING)
                    .append(priceFormat(customerPayAmountInPounds))
                    .append("\n**Thank you for visiting us!**\n\n");
        } else {
            throw new FinalizedBillModificationException();
        }
    }

    /**
     * Print string.
     *
     * @return the string
     */
    public String print() {
        if (!finalized) {
            throw new BillNotFinalizedException("Bill Has to be finalized to be able to print");
        }
        String print = lineItems.toString();
        System.out.println(print);
        return print;
    }

    /**
     * Item line item string.
     *
     * @param description the description
     * @param item        the item
     * @return the string
     */
    public String itemLineItem(String description, Item item) {
        return String.format("\n%s. %s %s: %s %s%s",
                ++itemNumber,
                description,
                item.getProductsCode().getCode(),
                item.getProductsCode().getDescription(),
                STERLING,
                item.getCostInPounds()
        );
    }

    /**
     * Gets customer pay amount in pounds.
     *
     * @return the customer pay amount in pounds
     */
    public BigDecimal getCustomerPayAmountInPounds() {
        return customerPayAmountInPounds;
    }
}
