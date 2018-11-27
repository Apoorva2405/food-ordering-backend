package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.States;
import org.upgrad.repositories.StateRepository;
import org.upgrad.repositories.UserRepository;


import javax.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{

    private final StateRepository stateRepository ;

    public AddressServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public AddressServiceImpl() {
        stateRepository = null;
    }

    // This method is used to check whether the user is logged in or not
    @Override
    public States isValidState(Integer id) {
        return stateRepository.isValidState(id);
    }

}
