package org.upgrad.services;

import org.upgrad.models.Address;
import org.upgrad.models.States;
import org.upgrad.models.User;

public interface AddressService {

    // Saves userDetails to users table.
    String isValidState(Integer id);

    // Saves Data in address table.
    Integer addAddress(Address address);

    // Count no of rows in address table
    Integer countAddress();

    Integer addUserAddress(String temp, Integer user_id, Integer address_id) ;


}
