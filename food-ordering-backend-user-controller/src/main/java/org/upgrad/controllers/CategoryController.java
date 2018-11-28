package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //When any user tries to access this endpoint, it should retrieve all the categories present in the database
    // by the category name
    @GetMapping("")
    ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    //If the category name entered by the user matches any category in the database, it should retrieve that category
    //If the category name entered by the user does not match any category name in the database,
    // return the JSON response "No Category by this name!"
    @GetMapping("/{categoryName}")
    ResponseEntity<?> getCategoryByName(@PathVariable("categoryName") String categoryName) {

        String count = String.valueOf(categoryService.getCategoryCount(categoryName));
        if (!String.valueOf(categoryService.getCategoryCount(categoryName)).equalsIgnoreCase("0")) {
            return new ResponseEntity<>(categoryService.getCategory(categoryName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Category by this name!", HttpStatus.NOT_FOUND);
        }
    }
}
