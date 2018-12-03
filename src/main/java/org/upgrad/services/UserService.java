package org.upgrad.services;

import org.upgrad.models.User;

import java.util.Optional;

/*
 * This UserService interface gives the list of all the service that exist in the user service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface UserService {

    // Finds Password corresponding to contactNumber.
    String findUserPassword(String contactNumber);

    // Finds Password corresponding to id.
    User getUserById(Integer id);

    // Finds User Details corresponding to contactNumber.
    User findUser(String contactNumber);

    // Finds User Details corresponding to id.
    User findUserId(Integer id);

    // Saves userDetails to users table.
    void addUserDetails(User newuser);

    // UpdateUserDetails
    User updateUser(String firstname, String lastname, Integer id);

    // UpdatePassword
    Integer updateUserPassword(String password, Integer id);
}
