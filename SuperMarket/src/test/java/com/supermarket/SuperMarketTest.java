package com.supermarket;

import com.supermarket.domain.Bill;
import com.supermarket.domain.Item;
import com.supermarket.domain.ProductCode;
import com.supermarket.observer.BillingConsole;
import com.supermarket.observer.BillingMachine;
import com.supermarket.offer.BuyNGetMOffer;
import com.supermarket.offer.BuyNGetMPercentOffDiscountOffer;
import com.supermarket.offer.BuyNGetMPoundOffDiscountOffer;
import com.supermarket.offer.Offer;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static com.supermarket.util.PriceUtil.priceFormat;

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

    private Bill bill;
    private BillingMachine offerObserver;

    /**
     * Sets .
     */
    @Before
    public void setup() {
        bill = new Bill();
        offerObserver = new BillingMachine(bill);
        offerObserver.subscribe(buy1Get1FreeOfferForTea);
        offerObserver.subscribe(buy3GetDot50OffForStrawberries);
        offerObserver.subscribe(buy3GetAtTwoThirdsOfferForCoffee);
    }

    /**
     * Green tea on offer test.
     */
    @Test
    public void greenTeaOnOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay GR1: Green Tea £3.11\n" +
                "2. Free! GR1: Green Tea £3.11\n" +
                "You only pay: £3.11\n" +
                "**Thank you for visiting us!**";

        BillingConsole billingConsole = new BillingConsole(offerObserver);
        billingConsole.addItem(greenTea);

        bill.finalizeBill();
        TestCase.assertEquals(expected, bill.print());
    }

    /**
     * Strawberry no offer test.
     */
    @Test
    public void strawberryNoOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay SR1: Strawberries £5.00\n" +
                "2. Pay SR1: Strawberries £5.00\n" +
                "You only pay: £10.00\n" +
                "**Thank you for visiting us!**";

        BillingConsole billingConsole = new BillingConsole(offerObserver);
        billingConsole.addItem(strawberries);
        billingConsole.addItem(strawberries);

        bill.finalizeBill();
        TestCase.assertEquals(expected, bill.print());
    }

    /**
     * Strawberry on offer test.
     */
    @Test
    public void strawberryOnOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay SR1: Strawberries £5.00\n" +
                "2. Pay SR1: Strawberries £5.00\n" +
                "3. Pay SR1: Strawberries £5.00 Buy 3 get .50%s discount - Offer Kicks in! -£1.50\n" +
                "4. Pay SR1: Strawberries £5.00 Buy 3 get .50%s discount -£0.50\n" +
                "5. Pay SR1: Strawberries £5.00 Buy 3 get .50%s discount -£0.50\n" +
                "6. Pay SR1: Strawberries £5.00 Buy 3 get .50%s discount -£0.50\n" +
                "7. Pay SR1: Strawberries £5.00 Buy 3 get .50%s discount -£0.50\n" +
                "You only pay: £31.50\n" +
                "**Thank you for visiting us!**";

        BillingConsole billingConsole = new BillingConsole(offerObserver);
        billingConsole.addItem(strawberries);
        billingConsole.addItem(strawberries);
        billingConsole.addItem(strawberries);
        billingConsole.addItem(strawberries);
        billingConsole.addItem(strawberries);
        billingConsole.addItem(strawberries);
        billingConsole.addItem(strawberries);

        bill.finalizeBill();
        TestCase.assertEquals(expected, bill.print());
    }

    /**
     * Coffee no offer test.
     */
    @Test
    public void coffeeNoOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay CF1: Coffee £11.23\n" +
                "2. Pay CF1: Coffee £11.23\n" +
                "You only pay: £22.46\n" +
                "**Thank you for visiting us!**";

        BillingConsole billingConsole = new BillingConsole(offerObserver);
        billingConsole.addItem(coffee);
        billingConsole.addItem(coffee);

        bill.finalizeBill();
        TestCase.assertEquals(expected, bill.print());
    }

    /**
     * Coffee on offer test.
     */
    @Test
    public void coffeeOnOfferTest() {
        final String expected = "Welcome to 'A Small Chain Of SuperMarket'\n" +
                "1. Pay CF1: Coffee £11.23\n" +
                "2. Pay CF1: Coffee £11.23\n" +
                "3. Pay CF1: Coffee £11.23 Buy 3+ and get all at 2/3 price Offer - Offer Kicks in! -£11.23\n" +
                "4. Pay CF1: Coffee £11.23 Buy 3+ and get all at 2/3 price Offer -£3.74\n" +
                "5. Pay CF1: Coffee £11.23 Buy 3+ and get all at 2/3 price Offer -£3.74\n" +
                "6. Pay CF1: Coffee £11.23 Buy 3+ and get all at 2/3 price Offer -£3.74\n" +
                "7. Pay CF1: Coffee £11.23 Buy 3+ and get all at 2/3 price Offer -£3.74\n" +
                "You only pay: £52.41\n" +
                "**Thank you for visiting us!**";

        BillingConsole billingConsole = new BillingConsole(offerObserver);
        billingConsole.addItem(coffee);
        billingConsole.addItem(coffee);
        billingConsole.addItem(coffee);
        billingConsole.addItem(coffee);
        billingConsole.addItem(coffee);
        billingConsole.addItem(coffee);
        billingConsole.addItem(coffee);

        bill.finalizeBill();
        TestCase.assertEquals("52.41", priceFormat(bill.getCustomerPayAmountInPounds()));
        TestCase.assertEquals(expected, bill.print());
    }
}
