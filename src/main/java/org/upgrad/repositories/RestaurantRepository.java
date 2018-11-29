package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;
import org.upgrad.models.Restaurant;
import org.upgrad.models.States;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    @Query(nativeQuery = true,value="SELECT * FROM RESTAURANT WHERE RESTAURANT_NAME=?1")
    Restaurant getRestaurantsByRestName(String name);

    @Query(nativeQuery = true,value="SELECT CATEGORY_ID FROM RESTAURANT_CATEGORY WHERE RESTAURANT_ID=?1")
    Integer getCategoryId(int id);

    @Query(nativeQuery = true,value="SELECT CATEGORY_NAME FROM CATEGORY WHERE ID=?1")
    String getCategories(int cat_id);

}





/* package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.Restaurant;

import java.util.List;

@Repository
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
}  */
