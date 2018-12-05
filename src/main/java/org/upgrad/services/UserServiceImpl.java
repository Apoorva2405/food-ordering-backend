package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.models.UserAuthToken;
import org.upgrad.repositories.UserAuthTokenRepository;
import org.upgrad.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;


/*
    This class contains implementation of all user related methods.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Finds password of user.
    @Override
    public String findUserPassword(String contactNumber) {
        return userRepository.findUserPassword(contactNumber);
    }

    // Finds details of user
    @Override
    public User findUser(String contactNumber) { return userRepository.findUser(contactNumber); }

    // Find details of user corresponding to userId
    @Override
    public User findUserId(Integer id) { return userRepository.findUserId(id); }

    // User details
    @Override
    public User getUserById(Integer id){ return userRepository.findUserPasswordId(id); }

    // Updates user Passwords
    @Override
    public Integer updateUserPassword(String password, Integer id) { return userRepository.updatePassword(password,id);} ;

    // Add new User details
    @Override
    public void addUserDetails(User newuser) {
        userRepository.addUserCredentials(newuser.getFirstName(), newuser.getLastName(), newuser.getEmail(),  newuser.getContactNumber(), newuser.getPassword());
    }

    // Updates user details
    @Override
    public User updateUser(String firstname, String lastname, Integer id)
    {
        if(null == lastname)
            userRepository.updateFirstName(firstname,id);
        else
            userRepository.updateDetails(firstname,lastname,id);


        User user  =  userRepository.findUserId(id);
        return user;
    }

}
