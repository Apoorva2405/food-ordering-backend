package org.upgrad.models;

import javax.persistence.*;

/*
 * Category model class contain all the attributes to be mapped to all the fields in the category table in the database.
 * Annotations are used to specify all the constraints to the table and table-columns in the database.
 * Here getter, setter and constructor are defined for this model class.
 */


@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    // Default Constructor
    public Category(){
    }

    // Parameterized Constructor
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    /*
        Getters & Setters
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
