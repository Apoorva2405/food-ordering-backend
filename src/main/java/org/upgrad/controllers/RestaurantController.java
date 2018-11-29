package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.upgrad.services.RestaurantService;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

        /**
         * Retreives all restaurants
         * @return returns all questions based on categoryId
         */
        @GetMapping("")
        public ResponseEntity<?> getAllRestaurants() {
            return new ResponseEntity<>(restaurantService.getAllRestaurant(), HttpStatus.OK);
        }

    @GetMapping("/name/{restaurantName}")
    public ResponseEntity<?> getAllRestaurantsByRestaurantName(@PathVariable String restaurantName, @RequestParam String accesstoken) {

            return new ResponseEntity<>(restaurantService.getRestaurantByName(restaurantName), HttpStatus.OK);
    }

    }
