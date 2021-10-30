package com.supermarket.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceUtil {
    public static String priceFormat(BigDecimal b) {
        return b.setScale(2, RoundingMode.HALF_EVEN).toEngineeringString();
    }
}
