package org.upgrad.models;

import javax.persistence.*;


@Entity
@Table(name="RESTAURANT")
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurant_name;

    @Column(name = "photo_url", nullable = false)
    private String photo_url;

    @Column(name = "user_rating", nullable = false)
    private Double user_rating;

    @Column(name = "average_price_for_two", nullable = false)
    private int average_price_for_two;

    @Column(name = "number_of_users_rated", nullable = false)
    private int number_of_users_rated;

    @Column(name = "address_id", nullable = false)
    private int address_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Number getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(Double user_rating) {
        this.user_rating = user_rating;
    }

    public int getAverage_price_for_two() {
        return average_price_for_two;
    }

    public void setAverage_price_for_two(int average_price_for_two) {
        this.average_price_for_two = average_price_for_two;
    }

    public int getNumber_of_users_rated() {
        return number_of_users_rated;
    }

    public void setNumber_of_users_rated(int number_of_users_rated) {
        this.number_of_users_rated = number_of_users_rated;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }


    //  @Transient
  //  private Category category;


    /*public int getAddress_id() {
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
  */

}
