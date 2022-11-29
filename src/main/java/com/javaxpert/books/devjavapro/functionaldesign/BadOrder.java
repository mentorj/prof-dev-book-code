package com.javaxpert.books.devjavapro.functionaldesign;

import java.util.List;

/**
 * A simple Order model
 * Too simple..
 * @author deadbrain ,jerome@javaxpert.com
 */
public class BadOrder {
    private String orderRef;
    private String customerEmail;
    private long orderedAt;

    private List<Product> products;
    private List<Coupon> coupons;
    private List<Integer> quantity;
    private float orderTotal;

    private String country;
    private String zipCode;
    private String addressLine1;
    private String addressLine2;

    private String relayStoreRef;


}
