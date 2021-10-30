package com.supermarket.domain;

import lombok.Getter;

public enum ProductCode {
    GR1("Green Tea"), SR1("Strawberries"), CF1("Coffee");
    @Getter
    private final String description;

    ProductCode(String description) {
        this.description = description;
    }

    public String getCode() {
        return this.toString();
    }
}
