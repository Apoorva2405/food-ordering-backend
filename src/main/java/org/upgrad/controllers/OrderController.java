package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.UserAuthToken;
import org.upgrad.services.OrderService;
import org.upgrad.services.OrderServiceImpl;
import org.upgrad.services.UserAuthTokenService;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserAuthTokenService userAuthTokenService;

    //If the coupon name entered by the user matches any coupon in the database, retrieve the coupon details

    @GetMapping("/coupon/{couponName}")
    ResponseEntity<?> getCouponByCouponName(@RequestParam(required = false) String couponName, @RequestHeader String accessToken) {

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);

        // Checking if user is logged in.
        if (usertoken == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null)  {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (orderService.getCoupon(couponName)!=null){
            return new ResponseEntity<>(orderService.getCoupon(couponName), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid Coupon!", HttpStatus.NOT_FOUND);
        }
    }
}
