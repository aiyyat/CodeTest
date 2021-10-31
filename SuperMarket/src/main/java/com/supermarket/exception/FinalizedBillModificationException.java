package com.supermarket.exception;

/**
 * The type Finalized bill cannot be modified exception.
 */
public class FinalizedBillModificationException extends SuperMarketException {

    /**
     * Instantiates a new Finalized bill cannot be modified exception.
     */
    public FinalizedBillModificationException() {
        super("Once Finalized a Bill Cannot be modified");
    }
}
