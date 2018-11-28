package org.upgrad.repositories;

import org.upgrad.models.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// This repository interface is responsible for the interaction between the user service with the user database

// CREATE TABLE ADDRESS(id SERIAL, flat_buil_number VARCHAR(255), locality VARCHAR(255),
// city VARCHAR(30),zipcode VARCHAR(30),
// state_id INTEGER ,PRIMARY KEY (id),FOREIGN KEY (state_id) REFERENCES STATES(id) ON DELETE CASCADE);
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    // For adding address in table.
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO Address (flat_buil_number,locality,city,zipcode,state_id) VALUES (?1,?2,?3,?4,?5)")
    Integer addAddress(String flat_buil_number, String locality, String city,String zipcode, Integer state_id );


    // For adding user details in users table.
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO User_Address (type,user_id,address_id) VALUES (?1,?2,?3)")
    Integer addUserAddress(String type, Integer user_id , Integer address_id);

    /*
       This selects state Name for the state_id.
    */
    @Query(nativeQuery = true,value = "SELECT count(*) FROM ADDRESS ")
    Integer countAddress();


}

