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

    // Finds User Details correcponding to contactNumber.
    User findUser(String contactNumber);

    // Saves userDetails to users table.
    void addUserDetails(User newuser);

}
