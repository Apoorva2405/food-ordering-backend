package org.upgrad.models;


import javax.persistence.*;

/*
 * USER_ADDRESS model class contain all the attributes to be mapped to all the fields in the USER_ADDRESS table in the database.
 * Annotations are used to specify all the constraints to the table and table-columns in the database.
 * Here getter, setter and constructor are defined for this model class.
 */
@Entity
@Table(name = "USER_ADDRESS")
public class UserAddress {

    // CREATE TABLE USER_ADDRESS(id SERIAL,type VARCHAR(10) DEFAULT 'temp',user_id
    // INTEGER NOT NULL,address_id INTEGER NOT NULL ,PRIMARY KEY (id), FOREIGN KEY (user_id) REFERENCES USERS(id)
    // ON DELETE CASCADE,FOREIGN KEY (address_id) REFERENCES ADDRESS(id) ON DELETE CASCADE);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String type = "TEMP";

    @Column(name = "user_id", nullable = false)
    private String user_id ;

    @Column(name = "address_id", nullable = false)
    private String address_id ;


}
