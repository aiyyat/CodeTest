package com.supermarket.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.supermarket.constants.SuperMarketConstants.STERLING;
import static com.supermarket.util.PriceUtil.priceFormat;

public class NetBill {
    private final StringBuilder lineItems = new StringBuilder("Welcome to 'A Small Chain Of SuperMarket'");
    private List<Item> items = new ArrayList<>();
    private BigDecimal costInPounds = new BigDecimal("0");
    private BigDecimal netDiscountInPounds = new BigDecimal("0");
    private BigDecimal netCostInPounds = new BigDecimal("0");
    private int itemNumber = 0;

    public void addItem(Item item) {
        items.add(item);
        netCostInPounds = netCostInPounds.add(item.getCostInPounds());
        lineItems.append(itemLineItem("Pay", item));
    }

    public void applyDiscount(String description, BigDecimal discountInPounds) {
        netDiscountInPounds = netDiscountInPounds.add(discountInPounds);
        lineItems.append(" ").append(description).append(" -").append(priceFormat(discountInPounds)).append(STERLING);
    }

    public void addFreebies(List<Item> freebies) {
        freebies.forEach(item -> {
            lineItems.append(itemLineItem("Free!", item));
        });
    }

    public String finalizeAndPrintBill() {
        lineItems
                .append("\nYou only pay: ")
                .append(priceFormat(netCostInPounds.subtract(netDiscountInPounds)))
                .append(STERLING)
                .append("\n**Thank you for visiting us!**");
        return lineItems.toString();
    }

    public String itemLineItem(String description, Item item) {
        return String.format("\n%s. %s %s: %s %s%s",
                ++itemNumber,
                description,
                item.getProductCode().getCode(),
                item.getProductCode().getDescription(),
                item.getCostInPounds(),
                STERLING);
    }
}
