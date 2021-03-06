package org.upgrad.requestResponseEntity;

import org.upgrad.models.Item;

/*
 * OrderItemResponse class contain all the attributes that are to be returned as a response.
 * Here getter, setter and constructor are defined for this response class.
 */

public class OrderItemResponse {

    private Integer id;

    private Item item;

    private Integer quantity;

    private Integer price;

    public OrderItemResponse(Integer id, Item item, Integer quantity, Integer price) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
