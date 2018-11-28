package org.upgrad.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="restaurant")
public class Restaurant implements Serializable{

    @Id
    private int id;

    private String restaurant_name;

    private String photo_url;

    private Number user_rating;

    private int average_price_for_two;

    private int number_of_users_rated;

    private int address_id;

    @Transient
    private Category category;


    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getNumber_of_users_rated() {
        return number_of_users_rated;
    }

    public void setNumber_of_users_rated(int number_of_users_rated) {
        this.number_of_users_rated = number_of_users_rated;
    }

    public int getAverage_price_for_two() {
        return average_price_for_two;
    }

    public void setAverage_price_for_two(int average_price_for_two) {
        this.average_price_for_two = average_price_for_two;
    }

    public Number getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(Number user_rating) {
        this.user_rating = user_rating;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
