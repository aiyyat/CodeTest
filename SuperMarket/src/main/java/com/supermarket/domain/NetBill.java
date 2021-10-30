package com.supermarket.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NetBill {
    private StringBuilder lineItems = new StringBuilder("Welcome to 'A Small Chain Of SuperMarket'");
    private List<Item> items = new ArrayList<>();
    private BigDecimal costInPounds = new BigDecimal("0");
    // Discount
    private BigDecimal discountInPounds = new BigDecimal("0");
    private BigDecimal netCostInPounds = new BigDecimal("0");

    public void addItem(Item item) {
        items.add(item);
        netCostInPounds.add(item.getCostInPounds());
        lineItems.append(String.format("%s: %s %s\n", item.getProductCode(), item.getDescription(), item.getCostInPounds()));
    }

    public void applyDiscount(String description, BigDecimal discountInPounds) {
        this.discountInPounds.add(discountInPounds);
        lineItems.append(description).append(" ").append(discountInPounds).append("\n");
    }

    public void addFreebees(List<Item> freebees) {
        freebees.forEach(item -> {
            lineItems.append(String.format("Free! %s: %s %s\n", item.getProductCode(), item.getDescription(), item.getCostInPounds()));
        });
    }

    public void finalizeAndPrintBill() {
        lineItems.append("Please pay").append(netCostInPounds.toEngineeringString()).append("\n**Thank you for visiting us!**");
        System.out.println(lineItems);
    }
}
