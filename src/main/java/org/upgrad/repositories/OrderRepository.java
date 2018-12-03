package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Order;
import org.upgrad.models.OrderItem;

import java.util.List;

// This repository interface is responsible for the interaction between the order service with the orders table

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    // Get order details by Id.
    @Query(nativeQuery = true,value = "SELECT * FROM ORDERS WHERE USER_ID=?1 ORDER BY DATE DESC")
    Iterable<Order> getOrderByUserId(Integer id);

    // Get order details by Id.
    @Query(nativeQuery = true,value = "SELECT * FROM ORDERS WHERE USER_ID=?1 ORDER BY DATE DESC")
    List<Order> getOrderByUser(Integer id);

}
