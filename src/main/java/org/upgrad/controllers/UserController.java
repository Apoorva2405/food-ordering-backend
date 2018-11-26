package org.upgrad.controllers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.UserAuthTokenService;
import org.upgrad.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;


    /*
     * This endpoint is used to signup a user.
     * Param - First name , Last name (optional) , Email , Contact number , Password
     */
    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<?> signup( @RequestParam String firstName,@RequestParam(required = false) String lastName , @RequestParam String email, @RequestParam String contactNumber, @RequestParam String password){

        // Finding if the user with corresponding contactNumber exists or not
        User user = userService.findUser(contactNumber);

         // If it exists, then send error
         if ( user != null) {
             return new ResponseEntity<>("Try any other contact number, this contact number has already been registered!",HttpStatus.BAD_REQUEST );
         }else {


              // Checks for valid email id -> x@x.co

               String EMAIL_PATTERN =
                     "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                             + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

             if ( ! email.matches(EMAIL_PATTERN) )
                 return new ResponseEntity<>("Invalid email-id format!",HttpStatus.BAD_REQUEST );


              // Checks for valid contactNumber.
              if( ! (contactNumber.length() == 10  && contactNumber.matches("[0-9]+")))
              {
                  return new ResponseEntity<>("Invalid contact number!",HttpStatus.BAD_REQUEST );
              }

              // Checks for strong password
              boolean hasUpper = false;
              boolean hasDigit = false;
              boolean specialChar = password.matches("[#@$%&*!^]+");

             String passpattern = "(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";

             if ( ! password.matches(passpattern) )
                 return new ResponseEntity<>("Weak password!",HttpStatus.BAD_REQUEST );
             }

             // Save details in Database.
            String passwordsha = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();

            User newuser = new User(firstName, lastName, contactNumber, email, passwordsha);

            userService.addUserDetails(newuser);
            String message =  "User with contact number " + contactNumber + " successfully registered!"  ;
            return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    /*
    * This endpoint is used to login a user.
    * Here contact number and password has to be provided to match the credentials.
    */
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestParam String contactNumber, @RequestParam String password){
        String passwordByUser = String.valueOf(userService.findUserPassword(contactNumber));
        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();
        if(userService.findUserPassword(contactNumber)==null) return new ResponseEntity<>("This contact number has not been registered!",HttpStatus.OK);
        else if (!(passwordByUser.equalsIgnoreCase(sha256hex))) {
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }
        else{
            User user = userService.findUser(contactNumber);
            String accessToken = UUID.randomUUID().toString();
            userAuthTokenService.addAccessToken(user.getId(),accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.add("access-token", accessToken);
            List<String> header = new ArrayList<>();
            header.add("access-token");
            headers.setAccessControlExposeHeaders(header);
            return new ResponseEntity<>(user,headers,HttpStatus.OK);
        }
    }

    /*
    * This endpoint is used to logout a user.
    * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
    */
    @PutMapping("/logout")
    @CrossOrigin
    public ResponseEntity<String> logout(@RequestHeader String accessToken){
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            userAuthTokenService.removeAccessToken(accessToken);
            return new ResponseEntity<>("You have logged out successfully!",HttpStatus.OK);}
    }


    /*
     * This endpoint is used to update user details
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     *
     */
    @PutMapping("/user")
    @CrossOrigin
    public ResponseEntity<String> userUpdate(@RequestHeader String accessToken){
     /*   if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            userAuthTokenService.removeAccessToken(accessToken); */
            return new ResponseEntity<>("You have logged out successfully!",HttpStatus.OK);
    }



    /*
    Update - “/api/user”

It should be a PUT request.
Access to this endpoint requires authentication.
This endpoint must request the following values from the user:
First name - String
Last name (Optional)- String
Access token - String

If the user is not logged in and tries to access this endpoint,
 return the JSON response "Please Login first to access this endpoint!" with the corresponding HTTP status.

If the user has already logged out and tries to log out again,
return the JSON response “You have already logged out. Please Login first to access this endpoint!”
with the corresponding HTTP status.

Once the user has successfully logged in, update the first name and
the last name of the user, then return the JSON response containing the updated
user information with the corresponding HTTP status. You can get the user’s information,
for which you need to change the names from the access token provided.

Here is what the response should look like after changing the  firstname and lastname fields:
     */

}
