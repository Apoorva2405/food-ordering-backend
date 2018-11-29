package org.upgrad.services;

import org.upgrad.models.Address;
import org.upgrad.models.States;

public interface AddressService {

    // Saves userDetails to users table.
    String isValidState(Integer id);

    // Saves Data in address table.
    Integer addAddress(Address address);

    // Count no of rows in address table
    Integer countAddress();

    Integer addUserAddress(String temp, Integer user_id, Integer address_id) ;

    Iterable<States> getAllStates() ;

    Address getaddressById( Integer addressId);

    Integer updateAddressById (String flat_build_num , String locality, String city, String zipcode , Integer state_id , Integer id);
}
