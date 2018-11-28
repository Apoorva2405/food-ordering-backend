package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.services.RestaurantService;

import javax.servlet.http.HttpSession;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

        /**
         * Retreives all restaurants
         * @return returns all questions based on categoryId
         */
        @GetMapping("/restaurant")
        public ResponseEntity<?> getAllRestaurants() {
            return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
        }

    @GetMapping("/api/restaurant/name/{restaurantName}")
    public ResponseEntity<?> getAllRestaurantsByRestaurantName(@PathVariable("restaurantName") String restaurantName, HttpSession session) {

            return new ResponseEntity<>(restaurantService.getAllRestaurantsByrestaurantName(restaurantName), HttpStatus.OK);
    }

    }
