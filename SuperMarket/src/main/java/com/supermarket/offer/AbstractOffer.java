package com.supermarket.offer;

/**
 * The type Abstract offer.
 */
public abstract class AbstractOffer implements IOffer {
    /**
     * Reset after Offering. Most implementation don't need this even though its a part of the offer contract,
     * hence others not forced to implement it but use it and booom!.
     */
    public void resetOffer() {
        throw new UnsupportedOperationException();
    }
}
