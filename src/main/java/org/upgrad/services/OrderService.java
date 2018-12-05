package org.upgrad.services;

import org.upgrad.models.Coupon;
import org.upgrad.models.Order;
import org.upgrad.models.OrderItem;
import org.upgrad.requestResponseEntity.PastOrderResponse;

import java.util.Date;
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

    //saveOrder with temporary address
    void addOrder(Integer orderId,double bill, Integer couponId, double discount, Date date, Integer paymentId,Integer userId, Integer addressId);

    //saveOrder with perm address
    void addOrderWithPermAddress(Integer orderId,double bill, Integer couponId, double discount, Date date, Integer paymentId,Integer userId, Integer addressId);

    //saveOrderItems
    void addOrderItem(Integer id, Integer orderId,Integer itemId,Integer quantity,Integer price);

    //find latest order id
    Integer findLatestOrderId();

    //find latest orderItem id
    Integer findLatestOrderItemId();
}
