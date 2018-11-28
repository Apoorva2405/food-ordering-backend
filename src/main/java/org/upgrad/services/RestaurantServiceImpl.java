package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Restaurant;
import org.upgrad.repositories.RestaurantRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Iterable<Restaurant> getAllRestaurants() {
        System.out.println("inside get all");
        return restaurantRepository.findAll();
    }


    @Override
    public Iterable<Restaurant> getAllRestaurantsByrestaurantId(int restaurantId) {
        return null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public Restaurant getAllRestaurantsByrestaurantName(String name) {
        Restaurant rest= restaurantRepository.getRestaurantsByRestName(name);
        System.out.println(rest);
        Restaurant res = new Restaurant();
        return res;
    }

    @Override
    public Iterable<Restaurant> getAllRestaurantsByCategory(String category) {
        return null;
    }
}
