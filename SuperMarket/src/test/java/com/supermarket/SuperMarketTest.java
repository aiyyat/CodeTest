package com.supermarket;

import com.supermarket.domain.Item;
import com.supermarket.domain.NetBill;
import com.supermarket.domain.ProductCode;
import com.supermarket.domain.observer.BillGenerator;
import com.supermarket.domain.observer.ItemFinalizer;
import com.supermarket.domain.offer.BuyNGetMOffer;
import com.supermarket.domain.offer.BuyNGetMPercentOffDiscountOffer;
import com.supermarket.domain.offer.BuyNGetMPoundOffDiscountOffer;
import com.supermarket.domain.offer.Offer;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Unit test for simple App.
 */
public class SuperMarketTest {
    private final Item greenTea = new Item(ProductCode.GR1, new BigDecimal("3.11"));
    private final Item strawberries = new Item(ProductCode.SR1, new BigDecimal("5.00"));
    private final Item coffee = new Item(ProductCode.CF1, new BigDecimal("11.23"));
    private final Offer buy3GetDot50OffForStrawberries = new BuyNGetMPoundOffDiscountOffer(
            "Buy 3 get .50%s discount",
            strawberries,
            3,
            new BigDecimal(".5"));
    private final Offer buy1Get1FreeOfferForTea = new BuyNGetMOffer(greenTea, 1, 1);
    private final Offer buy3GetAtTwoThirdsOfferForCoffee = new BuyNGetMPercentOffDiscountOffer(
            "Buy 3+ and get all at 2/3 price Offer",
            coffee,
            3,
            new BigDecimal(1).divide(new BigDecimal(3), new MathContext(5)));

    private NetBill netBill;
    private BillGenerator offerObserver;

    @Before
    public void setup() {
        netBill = new NetBill();
        offerObserver = new BillGenerator(netBill);
        offerObserver.subscribe(buy1Get1FreeOfferForTea);
        offerObserver.subscribe(buy3GetDot50OffForStrawberries);
        offerObserver.subscribe(buy3GetAtTwoThirdsOfferForCoffee);
    }

    @Test
    public void greenTeaOnOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay GR1: Green Tea 3.11£\n" +
                "2. Free! GR1: Green Tea 3.11£\n" +
                "You only pay: 3.11£\n" +
                "**Thank you for visiting us!**";

        ItemFinalizer itemFinalizer = new ItemFinalizer(offerObserver);
        itemFinalizer.addItem(greenTea);

        TestCase.assertEquals(expected, netBill.finalizeAndPrintBill());
    }

    @Test
    public void strawberryNoOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay SR1: Strawberries 5.00£\n" +
                "2. Pay SR1: Strawberries 5.00£\n" +
                "You only pay: 10.00£\n" +
                "**Thank you for visiting us!**";

        ItemFinalizer itemFinalizer = new ItemFinalizer(offerObserver);
        itemFinalizer.addItem(strawberries);
        itemFinalizer.addItem(strawberries);

        TestCase.assertEquals(expected, netBill.finalizeAndPrintBill());
    }

    @Test
    public void strawberryOnOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay SR1: Strawberries 5.00£\n" +
                "2. Pay SR1: Strawberries 5.00£\n" +
                "3. Pay SR1: Strawberries 5.00£ Buy 3 get .50%s discount - Offer Kicks in! -1.50£\n" +
                "4. Pay SR1: Strawberries 5.00£ Buy 3 get .50%s discount -0.50£\n" +
                "5. Pay SR1: Strawberries 5.00£ Buy 3 get .50%s discount -0.50£\n" +
                "6. Pay SR1: Strawberries 5.00£ Buy 3 get .50%s discount -0.50£\n" +
                "7. Pay SR1: Strawberries 5.00£ Buy 3 get .50%s discount -0.50£\n" +
                "You only pay: 31.50£\n" +
                "**Thank you for visiting us!**";

        ItemFinalizer itemFinalizer = new ItemFinalizer(offerObserver);
        itemFinalizer.addItem(strawberries);
        itemFinalizer.addItem(strawberries);
        itemFinalizer.addItem(strawberries);
        itemFinalizer.addItem(strawberries);
        itemFinalizer.addItem(strawberries);
        itemFinalizer.addItem(strawberries);
        itemFinalizer.addItem(strawberries);

        TestCase.assertEquals(expected, netBill.finalizeAndPrintBill());
    }

    @Test
    public void coffeeNoOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay CF1: Coffee 11.23£\n" +
                "2. Pay CF1: Coffee 11.23£\n" +
                "You only pay: 22.46£\n" +
                "**Thank you for visiting us!**";

        ItemFinalizer itemFinalizer = new ItemFinalizer(offerObserver);
        itemFinalizer.addItem(coffee);
        itemFinalizer.addItem(coffee);

        TestCase.assertEquals(expected, netBill.finalizeAndPrintBill());
    }

    @Test
    public void coffeeOnOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay CF1: Coffee 11.23£\n" +
                "2. Pay CF1: Coffee 11.23£\n" +
                "3. Pay CF1: Coffee 11.23£ Buy 3+ and get all at 2/3 price Offer - Offer Kicks in! -11.23£\n" +
                "4. Pay CF1: Coffee 11.23£ Buy 3+ and get all at 2/3 price Offer -3.74£\n" +
                "5. Pay CF1: Coffee 11.23£ Buy 3+ and get all at 2/3 price Offer -3.74£\n" +
                "6. Pay CF1: Coffee 11.23£ Buy 3+ and get all at 2/3 price Offer -3.74£\n" +
                "7. Pay CF1: Coffee 11.23£ Buy 3+ and get all at 2/3 price Offer -3.74£\n" +
                "You only pay: 52.41£\n" +
                "**Thank you for visiting us!**";

        ItemFinalizer itemFinalizer = new ItemFinalizer(offerObserver);
        itemFinalizer.addItem(coffee);
        itemFinalizer.addItem(coffee);
        itemFinalizer.addItem(coffee);
        itemFinalizer.addItem(coffee);
        itemFinalizer.addItem(coffee);
        itemFinalizer.addItem(coffee);
        itemFinalizer.addItem(coffee);

        TestCase.assertEquals(expected, netBill.finalizeAndPrintBill());
    }
}
