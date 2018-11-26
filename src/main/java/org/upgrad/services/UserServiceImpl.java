package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.models.UserAuthToken;
import org.upgrad.repositories.UserAuthTokenRepository;
import org.upgrad.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String findUserPassword(String contactNumber) {
        return userRepository.findUserPassword(contactNumber);
    }

    @Override
    public User findUser(String contactNumber) {
        return userRepository.findUser(contactNumber);
    }

    // This method is used to add access token details in the database
    /*@Override
    public void addUserDetails(String firstNA, String accessToken) {
        Optional<User> user = userRepository.findById(userId);
        Date date =new Date();
        UserAuthToken userAuthToken = new UserAuthToken(user.get(),accessToken,date);
        userAuthTokenRepository.save(userAuthToken);
    } */

    public void addUserDetails(User newuser) {
        userRepository.addUserCredentials(newuser.getFirstName(), newuser.getLastName(), newuser.getEmail(),  newuser.getContactNumber(), newuser.getPassword());
    }


}
