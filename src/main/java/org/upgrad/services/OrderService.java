package org.upgrad.services;

import org.upgrad.models.Coupon;

public interface OrderService {

    //Get coupon details by name.
    Coupon getCoupon(String couponName);
}
