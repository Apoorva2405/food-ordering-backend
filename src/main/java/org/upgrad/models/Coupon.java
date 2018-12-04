package org.upgrad.models;

import javax.persistence.*;

/*
 * Coupon model class contain all the attributes to be mapped to all the fields in the coupon table in the database.
 * Annotations are used to specify all the constraints to the table and table-columns in the database.
 * Here getter, setter and constructor are defined for this model class.
 */

@Entity
@Table(name = "COUPON")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "percent",nullable = false)
    private int percent;

    // Default Constructor
    public Coupon(){}

    // Parameterized Constructor
    public Coupon(String couponName, int percent) {
        this.couponName = couponName;
        this.percent = percent;
    }

    //Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

}
