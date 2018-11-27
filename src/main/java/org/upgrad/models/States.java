package org.upgrad.models;

import javax.persistence.*;


/*
 * STATES model class contain all the attributes to be mapped to all the fields in the STATES table in the database.
 * Annotations are used to specify all the constraints to the table and table-columns in the database.
 * Here getter, setter and constructor are defined for this model class.
 */

@Entity
@Table(name = "STATES")
public class States {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "state_name", nullable = false)
    private String state_name;

    /*
        Getters & Setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }


}
