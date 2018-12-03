package org.upgrad.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/*
 * Payment model class contain all the attributes to be mapped to all the fields in the payment table in the database.
 * Annotations are used to specify all the constraints to the table and table-columns in the database.
 * Here getter, setter and constructor are defined for this model class.
 */


@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_name")
    private String paymentName;

    //Default constructor
    public Payment(){}

    //Parameterized constructor
    public Payment(String paymentName) {
        this.paymentName = paymentName;
    }

    //Getter & Setter methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }
}
