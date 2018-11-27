package org.upgrad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/*
 * ADDRESS model class contain all the attributes to be mapped to all the fields in the ADDRESS table in the database.
 * Annotations are used to specify all the constraints to the table and table-columns in the database.
 * Here getter, setter and constructor are defined for this model class.
 */
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "flat_buil_number", nullable = false)
    private String flat_buil_number;

    @Column(name = "locality",nullable = false)
    private String locality;

    @Column(name = "city",nullable = false)
    private String city;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "state_id",nullable = false)
    private Integer state_id;

    // Default constructor
    public Address(){
    }

    // Parameterized Constructor
    public Address(String flat_buil_number, String locality, String city, String zipcode, Integer state_id) {
        this.flat_buil_number = flat_buil_number ;
        this.city = city ;
        this.locality = locality ;
        this.zipcode = zipcode ;
        this.state_id = state_id ;
    }

    /*
        Getter & Setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlat_buil_number() {
        return flat_buil_number;
    }

    public void setFlat_buil_number(String flat_buil_number) {
        this.flat_buil_number = flat_buil_number;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Integer getState_id() {
        return state_id;
    }

    public void setState_id(Integer state_id) {
        this.state_id = state_id;
    }



}
