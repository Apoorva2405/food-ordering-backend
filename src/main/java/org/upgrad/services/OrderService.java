package org.upgrad.services;

import org.upgrad.models.Coupon;
import org.upgrad.models.Order;
import org.upgrad.models.OrderItem;
import org.upgrad.requestResponseEntity.PastOrderResponse;

import java.util.List;

/*
 * This OrderService interface gives the list of all the service that exist in the order service implementation class.
 * Controller class will be calling the service methods by this interface.
 */

public interface OrderService {

    //Get coupon details by name.
    Coupon getCoupon(String couponName);

    //Get orderItem details by orderId
    Iterable<OrderItem> getOrderItemsByOrderId(Integer id);

    //Get orders response details by UserId
    Iterable<PastOrderResponse> getOrdersResponseByUser(Integer id);

    //Get orders details by UserId
    List<Order> getOrdersByUser(Integer id);
}
