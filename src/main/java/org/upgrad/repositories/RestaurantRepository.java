package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.Restaurant;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {

    @Query(nativeQuery = true,value="SELECT * FROM RESTAURANT WHERE RESTAURANT_NAME=?1")
    Restaurant getRestaurantsByRestName(String name);

    @Query(nativeQuery = true,value="select * from restaurant where restaurant_id=?1")
    Iterable<Restaurant> getRestaurantsByRestId(int id);

    @Query(nativeQuery = true,value="select * from category where category_name=?1")
    Iterable<Restaurant> getCategoryIdByCategoryName(String catName);

    @Query(nativeQuery = true,value="select * from category where category_id=?1")
    Iterable<Restaurant> getRestaurantIdByCategoryId(int cat_id);

    @Query(nativeQuery = true,value="SELECT * FROM RESTAURANT")
    Iterable<Restaurant> getAllRestaurants();
}
