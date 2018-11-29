package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
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
    public Iterable<Restaurant> getAllRestaurant() {
        System.out.println("inside get all");
        Iterable<Restaurant> rest = restaurantRepository.findAll();
        for (Restaurant restaurant: rest) {
            System.out.println("restaurant.getId() "+restaurant.getId());
            Integer catId = restaurantRepository.getCategoryId(restaurant.getId());
            System.out.print("catId "+catId);
            if (catId != null) {
                String cat = restaurantRepository.getCategories(catId);
//                System.out.println("cat "+cat);
//                category.setCategoryName(cat);
//                System.out.println("category "+category);
//                System.out.println("category get"+category.getCategoryName());
//                restaurant.setCategories(category);
            }
        }
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
    public Restaurant getRestaurantByName(String name) {
       // String name1  = "Lion Heart";
       // Restaurant rest= restaurantRepository.getRestaurantsByRestName(name1);
        Restaurant rest= restaurantRepository.getRestaurantsByRestName(name);

        System.out.println(rest);
        Restaurant res = new Restaurant();
        return res;
    }

    @Override
    public Iterable<Restaurant> getRestaurantByCategory(String category) {
        return null;
    }

    @Override
    public Restaurant getRestaurantDetails(int id) {
        return null;
    }
}
