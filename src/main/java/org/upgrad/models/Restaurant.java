package org.upgrad.models;

import javax.persistence.*;


@Entity
@Table(name="RESTAURANT")
public class Restaurant{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @Column(name = "user_rating", nullable = false)
    private Double userRating;

    @Column(name = "average_price_for_two", nullable = false)
    private int avgPrice;

    @Column(name = "number_of_users_rated", nullable = false)
    private int numberUsersRated;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER)

    @Transient
    private Category categories;

//    private String categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurant_name) {
        this.restaurantName = restaurantName;
    }

    public Number getUserRating() {
        return userRating;
    }

    public void setUserRating(Double user_rating) {
        this.userRating = userRating;
    }


//    public String getCategories() {
//        return categories;
//    }

//    public void setCategories(String categories) {
//        this.categories = categories;
//    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getNumberUsersRated() {
        return numberUsersRated;
    }

    public void setNumberUsersRated(int numberUsersRated) {
        this.numberUsersRated = numberUsersRated;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category category) {
        this.categories = category;
    }



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
