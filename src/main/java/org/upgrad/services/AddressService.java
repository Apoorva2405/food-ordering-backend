package org.upgrad.services;

import org.upgrad.models.Address;
import org.upgrad.models.States;

/*
 * This AddressService interface gives the list of all the service that exist in the address service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface AddressService {

    // Returns state corresponding to stateid
    States isValidState(Integer id);

    // Adds new Address
    Integer addAddress(Address address);

    // Give max addressId present in DB so far.
    Integer countAddress();

    // Adds user Address
    Integer addUserAddress(String temp, Integer user_id, Integer address_id) ;

    // Iterates & returns all states
    Iterable<States> getAllStates() ;

    // Get address details corresponding to addressId
    Address getaddressById( Integer addressId);

    // Returns true if address is present
    Boolean getAddress( Integer addressId);

    // Update address already present.
    Integer updateAddressById (String flat_build_num , String locality, String city, String zipcode , Integer state_id , Integer id);

    // Delete Address already present.
    Integer deleteAddressById (Integer id );

    //Delete Address already present.
    Integer deleteUserAddressById(Integer id);

    // Iterates & retrevies prem address details of the user.
    Iterable<Address>  getPermAddress(Integer userId) ;

}
