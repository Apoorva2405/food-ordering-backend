package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Restaurant;

public interface RestaurantService {

    Iterable<Restaurant> getAllRestaurants();
    Iterable<Restaurant> getAllRestaurantsByrestaurantId(int restaurantId);
    Restaurant getAllRestaurantsByrestaurantName(String name);
    Iterable<Restaurant> getAllRestaurantsByCategory(String category);

}
