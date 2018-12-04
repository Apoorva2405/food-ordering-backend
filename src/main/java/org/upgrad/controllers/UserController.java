package org.upgrad.controllers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.models.UserAuthToken;
import org.upgrad.services.UserAuthTokenService;
import org.upgrad.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;


    /**
     * This is POST API that sigup new user
     * @param firstName firstname of the user
     * @param lastName lastname of the user
     * @param email email of the user
     * @param contactNumber contact number of user
     * @param password password of user
     * @return whether user is signup or not.
     */
    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<?> signup(@RequestParam String firstName, @RequestParam(required = false) String lastName, @RequestParam String email, @RequestParam String contactNumber, @RequestParam String password) {

        // Finding if the user with corresponding contactNumber exists or not
        User user = userService.findUser(contactNumber);

        // If user already exsits
        if (null != user) {
            return new ResponseEntity<>("Try any other contact number, this contact number has already been registered!", HttpStatus.BAD_REQUEST);
        } else {
            // Checks for valid email id -> x@x.co
            String EMAIL_PATTERN =
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

            if (!email.matches(EMAIL_PATTERN))
                return new ResponseEntity<>("Invalid email-id format!", HttpStatus.BAD_REQUEST);

            // Checks for valid contactNumber.
            if (!(contactNumber.length() == 10 && contactNumber.matches("[0-9]+"))) {
                return new ResponseEntity<>("Invalid contact number!", HttpStatus.BAD_REQUEST);
            }

            // Checks for strong password
            String passpattern = "(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";

            if (!password.matches(passpattern))
                return new ResponseEntity<>("Weak password!", HttpStatus.BAD_REQUEST);
        }

        // Save details in Database.
        String passwordsha = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();

        User newuser = new User(firstName, lastName, contactNumber, email, passwordsha);

        userService.addUserDetails(newuser);
        String message = "User with contact number " + contactNumber + " successfully registered!";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * This is POST API that login a user if it is valid user
     * @param contactNumber contact number of user
     * @param password password of user
     * @return accessToken (unique identifier) which identifies whether user is logged in or not.
     */
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestParam String contactNumber, @RequestParam String password) {
        String passwordByUser = String.valueOf(userService.findUserPassword(contactNumber));
        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();
        // Find if user already exists
        if (userService.findUserPassword(contactNumber) == null)
            return new ResponseEntity<>("This contact number has not been registered!", HttpStatus.OK);
        else if (!(passwordByUser.equalsIgnoreCase(sha256hex))) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        } else {
            // Find valid user and returns its access token.
            User user = userService.findUser(contactNumber);
            String accessToken = UUID.randomUUID().toString();
            userAuthTokenService.addAccessToken(user.getId(), accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.add("access-token", accessToken);
            List<String> header = new ArrayList<>();
            header.add("access-token");
            headers.setAccessControlExposeHeaders(header);
            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        }
    }


    /**
     * This is POST API that logout a user if already login
     * @Header accessToken that identify whether user is logged in or not
     * @return whether user is logged out or not
     */
    @PutMapping("/logout")
    @CrossOrigin
    public ResponseEntity<String> logout(@RequestHeader String accessToken) {
        if (userAuthTokenService.isUserLoggedIn(accessToken) == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            // Remove AccessToken from DB.
            userAuthTokenService.removeAccessToken(accessToken);
            return new ResponseEntity<>("You have logged out successfully!", HttpStatus.OK);
        }
    }


    /*
     * This endpoint is used to update user details
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     *
     */
    @PutMapping("")
    @CrossOrigin
    public ResponseEntity<?> userUpdate(@RequestParam String firstName, @RequestParam(required = false) String lastName, @RequestHeader String accessToken) {

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);
        // Checking if user is logged in.
        if (usertoken == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null)  {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {

            Integer userId =  userAuthTokenService.getUserId(accessToken) ;
            User user = userService.updateUser(firstName, lastName, userId) ;

            // Updating user details.
            if ( user != null) ;
            {
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }
        }
    }

    /*
     * This endpoint is used to update user details
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     *
     */
    @PutMapping("/password")
    @CrossOrigin
    public ResponseEntity<String> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestHeader String accessToken) {

        boolean flag = false;
        String passpattern = "(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);
        // Checking if user is logged in.
        if (null == usertoken) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } // checking if user is not logged out.
        else if (null != userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()) {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {


            Integer userId =  userAuthTokenService.getUserId(accessToken) ;

            // Checking if oldpassword matches with password strored in database.
            User user = userService.getUserById(userId);

            String passwordByUser = user.getPassword() ;

            String oldpasswordsha = Hashing.sha256()
                    .hashString(oldPassword, Charsets.US_ASCII)
                    .toString();
            if (!(passwordByUser.equalsIgnoreCase(oldpasswordsha))) {
                return new ResponseEntity<>("Your password did not match to your old password!", HttpStatus.UNAUTHORIZED);
            } else if (!newPassword.matches(passpattern)) {
                return new ResponseEntity<>("Weak password!", HttpStatus.BAD_REQUEST);
            } else {

                String newpasswordsha = Hashing.sha256()
                        .hashString(newPassword, Charsets.US_ASCII)
                        .toString();


                if (null != userService.updateUserPassword(newpasswordsha, userId))
                    flag = true;
            }

            return new ResponseEntity<>("Password updated successfully!", HttpStatus.OK);
        }
    }
}

