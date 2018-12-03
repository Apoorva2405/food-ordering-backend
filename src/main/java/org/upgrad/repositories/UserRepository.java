package org.upgrad.repositories;

import org.upgrad.models.States;
import org.upgrad.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.UserAddress;

// This repository interface is responsible for the interaction between the user service with the user database

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    // Find password on bases of contactnumber.
    @Query(nativeQuery = true,value="SELECT PASSWORD FROM USERS WHERE contact_number=?1")
    String findUserPassword(String contactNumber);

    // Find password on bases of contactnumber.
    @Query(nativeQuery = true,value="SELECT * FROM USERS WHERE id=?1")
    User findUserPasswordId(Integer id);

    // Find user details on bases of contactnumber.
    @Query(nativeQuery = true,value = "SELECT * FROM USERS WHERE contact_number=?1")
    User findUser(String contactNumber);

    // Find user details on bases of userId.
    @Query(nativeQuery = true,value = "SELECT * FROM USERS WHERE id=?1")
    User findUserId(Integer id);

    // For adding user details in users table.
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT INTO USERS (firstname,lastname,contact_number,email,password) VALUES (?1,?2,?3,?4,?5)")
    void addUserCredentials(String fname, String lname, String contactnumber,String email, String password );

    // Method to update details for particular user.
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="UPDATE USERS SET firstname =?1 , lastname=?2  WHERE id=?3")
    Integer updateDetails( String firstname, String lastname, Integer id);

    // Method to update firstname for particular user.
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="UPDATE USERS SET firstname =?1  WHERE id=?2")
    Integer updateFirstName( String firstname, Integer id);

    // Method to update password for particular user.
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="UPDATE USERS SET password =?1  WHERE id=?2")
    Integer updatePassword( String password, Integer id);

}

