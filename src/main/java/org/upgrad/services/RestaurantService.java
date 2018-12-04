package org.upgrad.services;

import org.upgrad.models.Restaurant;
//import org.upgrad.models.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

public interface RestaurantService {

    Iterable<RestaurantResponse> getAllRestaurant();
    Iterable<RestaurantResponse> getRestaurantByName(String name);
    Iterable<RestaurantResponse> getRestaurantByCategory(String category);
    RestaurantResponseCategorySet getRestaurantDetails(int id);
    Restaurant updateRating(int restaurantId, int rating);
}
