package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Restaurant;
import org.upgrad.repositories.RestaurantRepository;
import org.upgrad.repositories.StateRepository;
import org.upgrad.repositories.UserAuthTokenRepository;
import org.upgrad.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;



    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public Iterable<Restaurant> getAllRestaurants() {
        System.out.println("inside get all");
        Iterable<Restaurant> rest = restaurantRepository.findAll();
        System.out.println("rest "+rest);
        return rest;
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
       // String name1  = "Lion Heart";
       // Restaurant rest= restaurantRepository.getRestaurantsByRestName(name1);
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
