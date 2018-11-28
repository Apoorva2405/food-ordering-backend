package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Address;
import org.upgrad.repositories.StateRepository;
import org.upgrad.repositories.AddressRepository;


import javax.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

    private final StateRepository stateRepository ;
    private final AddressRepository addressRepository;
   // private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, StateRepository stateRepository) {
        this.addressRepository = addressRepository;
        this.stateRepository = stateRepository;
    }

    // Add address to add address to address table.
    @Override
    public Integer addAddress(Address address)
    {
        return addressRepository.addAddress(address.getFlat_buil_number(), address.getLocality(), address.getCity() , address.getZipcode() , address.getState_id());
    }

    // This method is used to check whether the user is logged in or not.
    @Override
    public String isValidState(Integer id) {
        return stateRepository.isValidState(id);
    }

    @Override
    public Integer countAddress(){
        return addressRepository.countAddress() ;
    }

    @Override
    public Integer addUserAddress(String temp, Integer user_id, Integer address_id)
    {
        return addressRepository.addUserAddress(temp, user_id, address_id);
    }

}
