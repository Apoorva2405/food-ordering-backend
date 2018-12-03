package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.OrderItem;

// This repository interface is responsible for the interaction between the orderItem service with the order_item table
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    // Get orderItem details by OrderId.
    @Query(nativeQuery = true,value = "SELECT a.* FROM ORDER_ITEM a,ORDERS b WHERE b.ID=a.ORDER_ID and ORDER_ID=?1 ")
    Iterable<OrderItem> getOrderItemsByOrderId(Integer id);
}
