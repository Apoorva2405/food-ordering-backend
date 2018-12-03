package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.*;
import org.upgrad.requestResponseEntity.PastOrderResponse;
import org.upgrad.services.*;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
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
        } else if (orderService.getCoupon(couponName)!= null){
            return new ResponseEntity<>(orderService.getCoupon(couponName), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Invalid Coupon!", HttpStatus.NOT_FOUND);
        }
    }

    //Retrieve all the past orders from the user sorted by their order date, with the newest order first

    @GetMapping("")

    ResponseEntity<?> getPastOrdersOfUser(@RequestHeader String accessToken) {

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);
        // Checking if user is logged in.
        if (usertoken == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null)  {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            Integer userId = userAuthTokenService.getUserId(accessToken);
            Iterable<PastOrderResponse> orderResponse = orderService.getOrdersResponseByUser(userId);
            if (orderResponse != null && orderResponse.iterator().hasNext()) {
                // if user is ordered then display order details
                return new ResponseEntity<>(orderResponse, HttpStatus.OK);
            }
            else { // if user is not ordered anything display message.
                return new ResponseEntity<>("No orders have been made yet!", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
