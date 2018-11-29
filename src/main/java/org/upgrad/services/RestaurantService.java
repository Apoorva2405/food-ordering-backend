package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.Restaurant;

public interface RestaurantService {

    Iterable<Restaurant> getAllRestaurant();
    Iterable<Restaurant> getAllRestaurantsByrestaurantId(int restaurantId);
    Restaurant getRestaurantByName(String name);
    Iterable<Restaurant> getRestaurantByCategory(String category);
    Restaurant getRestaurantDetails(int id);

}
