package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Coupon;
import org.upgrad.repositories.CouponRepository;
import org.upgrad.repositories.OrderRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private final CouponRepository couponRepository;

    public OrderServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Coupon getCoupon(String couponName){
        return couponRepository.getCouponByName(couponName);
    }


}
