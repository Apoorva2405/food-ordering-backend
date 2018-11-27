package org.upgrad.controllers;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.models.UserAuthToken;
import org.upgrad.services.AddressService;
import org.upgrad.services.UserAuthTokenService;
import org.upgrad.services.UserService;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;


/*


    Here, the default address type is “temp”. So, if you need to save an
    address as the permanent address, you need to pass the type as “perm” string here.



    If the zip code provided by the user is not in the correct format, i.e., it does not contain
     only numbers and its length is more or less than six digits), return the JSON response
     "Invalid zip code!" with the corresponding HTTP status.

    If no state exists with the state id provided by user,
    return the JSON response "No state by this state id!" with the corresponding HTTP status.

    If the user has logged in successfully, add the complete address in the database, then return the
    JSON response "Address has been saved successfully!" with the corresponding HTTP status code 201.

    Also, the user table is connected to the address table by the user_address table. So, add data to that table accordingly.
 */



    /*
     * This endpoint is used to signup a user.
     * Param - First name , Last name (optional) , Email , Contact number , Password
     */
    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> address(@RequestParam String flatBuildingNumber, @RequestParam String locality, @RequestParam String city, @RequestParam Integer state_id, @RequestParam String zip, @RequestParam(required = false) String type , @RequestParam String accesstoken) {

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accesstoken);
        // Checking if user is logged in.
        if (usertoken == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accesstoken).getLogoutAt() != null) {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            Integer userId = userAuthTokenService.getUserId(accesstoken);

            if (!(zip.length() == 6 && zip.matches("[0-9]+"))) {
                return new ResponseEntity<>("Invalid zip code!", HttpStatus.BAD_REQUEST);
            }
            System.out.println(addressService.isValidState(state_id) );

            if( addressService.isValidState(state_id) == null) {
                return new ResponseEntity<>("No state by this state id!", HttpStatus.BAD_REQUEST);
            }

            // TODO : State check from
        }


        return new ResponseEntity<>("Invalid zip code!", HttpStatus.BAD_REQUEST);
    }



}
