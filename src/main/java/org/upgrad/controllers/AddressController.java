package org.upgrad.controllers;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Address;
import org.upgrad.models.User;
import org.upgrad.models.UserAddress;
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
     * This endpoint is used to signup a user.
     * Param - First name , Last name (optional) , Email , Contact number , Password
     */
    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> address(@RequestParam String flatBuildingNumber, @RequestParam String locality, @RequestParam String city, @RequestParam Integer state_id, @RequestParam String zip, @RequestParam(required = false) String type , @RequestParam String accesstoken) {

        String message = "" ;
        HttpStatus httpStatus = HttpStatus.OK ;

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accesstoken);
        // Checking if user is logged in.
        if (usertoken == null) {
            message = "Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accesstoken).getLogoutAt() != null) {
            message = "You have already logged out. Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } else {
            Integer userId = userAuthTokenService.getUserId(accesstoken);

            if (zip.length() == 6 && zip.matches("[0-9]+")) {
                System.out.println("State Id" + state_id);

                // Check for valid state Id
                if( addressService.isValidState(state_id) == null) {
                    message = "No state by this state id!" ;
                    httpStatus = HttpStatus.BAD_REQUEST ;
                } else
                {

                    int addressId  = addressService.countAddress() + 1 ;
                    String type1 = "temp" ;
                    // Save data in address table.
                    Address address = new Address(addressId ,flatBuildingNumber, locality, city, zip, state_id);
                    addressService.addAddress(address);

                    // Setting value of type parameter
                        if ( type !=null )
                            type1 = type ;

                        // Save data in userAddress table
                        addressService.addUserAddress(type1, userId, addressId) ;

                    message ="Address has been saved successfully!" ;
                    httpStatus = HttpStatus.CREATED ;
                }
            } else {
                message = "Invalid zip code!" ;
                httpStatus = HttpStatus.BAD_REQUEST ;
            }
        }
        return new ResponseEntity<>(message , httpStatus);
    }


    @GetMapping("/user")
    @CrossOrigin
    public ResponseEntity<?> getAllPermanentAddress(@RequestParam String accesstoken) {

        String message = "" ;
        HttpStatus httpStatus = HttpStatus.OK ;

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accesstoken);
        // Checking if user is logged in.
        if (usertoken == null) {
            message = "Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accesstoken).getLogoutAt() != null) {
            message = "You have already logged out. Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } else {
            Integer userId = userAuthTokenService.getUserId(accesstoken);



        }


        return new ResponseEntity<>(message , httpStatus);
    }

    /*
    Get All Permanent Addresses - “/api/address/user”

    It should be a GET request.

    Access to this endpoint requires authentication.

    This endpoint must request the following value from the user:

        Access token - String

    If the user is not logged in and tries to access this endpoint,
    return the JSON response "Please Login first to access this endpoint!" with the corresponding HTTP status.

    If the user has already logged out and tries to log out again,
    return the JSON response “You have already logged out. Please
     Login first to access this endpoint!” with the corresponding HTTP status.

    If the user has logged in successfully, and:

        If this user has not provided a  permanent address,
        return the JSON response "No permanent address found!" with the corresponding HTTP status.

        If there are some permanent addresses saved for
         this user in the database, retrieve all the
          permanent addresses and display the response in a JSON format with the corresponding HTTP status.

        Here is a link to a sample JSON response.
     */


    /*
     This is used to get details of all states.
     */
    @GetMapping("/user")
    @CrossOrigin
    public ResponseEntity<?> getAllPermanentAddress() {
        return new ResponseEntity<>( addressService.getAllStates() , HttpStatus.OK);
    }

}
