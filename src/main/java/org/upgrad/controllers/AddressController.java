package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Address;
import org.upgrad.models.UserAuthToken;
import org.upgrad.services.AddressService;
import org.upgrad.services.UserAuthTokenService;

import javax.swing.plaf.synth.SynthTextAreaUI;

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
    public ResponseEntity<?> address(@RequestParam String flatBuilNo, @RequestParam String locality, @RequestParam String city, @RequestParam Integer stateId, @RequestParam String zipcode, @RequestParam(required = false) String type , @RequestHeader String accessToken) {

        String message = "" ;
        HttpStatus httpStatus = HttpStatus.OK ;

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);
        // Checking if user is logged in.
        if (usertoken == null) {
            message = "Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            message = "You have already logged out. Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } else {
            Integer userId = userAuthTokenService.getUserId(accessToken);

            if (zipcode.length() == 6 && zipcode.matches("[0-9]+")) {

                // Check for valid state Id
                if( addressService.isValidState(stateId) == null) {
                    message = "No state by this state id!" ;
                    httpStatus = HttpStatus.BAD_REQUEST ;
                } else
                {

                    int addressId  = addressService.countAddress() + 1 ;
                    String type1 = "temp" ;
                    // Save data in address table.
                    Address address = new Address(addressId ,flatBuilNo, locality, city, zipcode, stateId);
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
                message = "Invalid zipcode!" ;
                httpStatus = HttpStatus.BAD_REQUEST ;
            }
        }
        return new ResponseEntity<>(message , httpStatus);
    }

    /*
    This is used to update address corresponding to addressId
    */
    @PutMapping("/{addressId}")
    @CrossOrigin
    public ResponseEntity<?> updateAddressById(@PathVariable Integer addressId , @RequestParam(required = false) String flatBuilNo , @RequestParam(required = false) String locality , @RequestParam(required = false) String city , @RequestParam(required = false) String zipcode , @RequestParam(required = false) Integer stateId , @RequestHeader String accessToken) {

        String message = "" ;
        HttpStatus httpStatus = HttpStatus.OK ;

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);

        System.out.println(" USER TOKEN "+ usertoken) ;
        // Checking if user is logged in.
        if (usertoken == null) {
            message = "Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            message = "You have already logged out. Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } else {
            Integer userId = userAuthTokenService.getUserId(accessToken);

            // Zipcode Validation check
            if (zipcode != null ){
                if (! ( zipcode.length() == 6 && zipcode.matches("[0-9]+") )) {
                    return new ResponseEntity<>("Invalid zipcode!" , HttpStatus.BAD_REQUEST);
                }
            }
            // Valid State check
            boolean validState  = false ;

            System.out.println("Sugandha" + stateId);
            System.out.println("Sugandha" + stateId.toString());


            System.out.println("Sugandha valid state" +  addressService.isValidState(stateId));

            if (stateId != null) {
                if (addressService.isValidState(stateId) == null) {
                    message = "No state by this state id!";
                    httpStatus = HttpStatus.BAD_REQUEST;
                } else
                    validState = true;
            }

            if (validState == true) {

                // Check if address exists for supplied addressId
                Boolean  add = addressService.getAddress(addressId);

                if (add == null) {
                    message = "No address with this address id!";
                    httpStatus = HttpStatus.BAD_REQUEST;
                } else {
                   /*  if (flatBuildingNumber == null)
                        flatBuildingNumber = add.getFlat_buil_number();

                    if (locality == null)
                        locality = add.getLocality();

                    if (city == null)
                        city = add.getCity();

                    if (zipcode == null)
                        zipcode = add.getZipcode();

                    if (state_id == null)
                        state_id = add.getState_id();  */

                    // Update Address
                    addressService.updateAddressById(flatBuilNo, locality, city, zipcode, stateId, addressId);

                    message = "Address has been updated successfully!";
                    httpStatus = HttpStatus.OK ;
                }
            }
        }
        return new ResponseEntity<>(message , httpStatus);
    }


    /*
     This is used to delete address corresponding to addressId
     */
    @DeleteMapping("/{addressId}")
    @CrossOrigin
    public ResponseEntity<?> deleteAddressById(@PathVariable Integer addressId , @RequestHeader String accessToken) {
        System.out.println("add: " + addressId);
        System.out.println("aT: " + accessToken);

        String message = "";
        HttpStatus httpStatus = HttpStatus.OK;

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);

        System.out.println(" USER TOKEN " + usertoken);
        // Checking if user is logged in.
        if (usertoken == null) {
            message = "Please Login first to access this endpoint!";
            httpStatus = HttpStatus.UNAUTHORIZED;
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            message = "You have already logged out. Please Login first to access this endpoint!";
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else {

            System.out.println("HERE" + addressId) ;


            // Check if address exists for supplied addressId
            Boolean add = addressService.getAddress(addressId);

            if (add == false) {
                message = "No address with this address id!";
                httpStatus = HttpStatus.BAD_REQUEST;
            } else {

                // Delete address from both tables.
                addressService.deleteAddressById(addressId) ;
                addressService.deleteUserAddressById(addressId);

                message = "Address has been deleted successfully!" ;
                httpStatus = HttpStatus.OK ;
            }
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



   @GetMapping("/user")
    @CrossOrigin
    public ResponseEntity<?> getAllPermanentAddress(@RequestHeader String accessToken) {

        String message = "" ;
        HttpStatus httpStatus = HttpStatus.OK ;

        UserAuthToken usertoken = userAuthTokenService.isUserLoggedIn(accessToken);
        // Checking if user is logged in.
        if (usertoken == null) {
            message = "Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } // checking if user is not logged out.
        else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            message = "You have already logged out. Please Login first to access this endpoint!" ;
            httpStatus =  HttpStatus.UNAUTHORIZED ;
        } else {
            Integer userId = userAuthTokenService.getUserId(accessToken);

            if ( addressService.getPermAddress(userId)  == null )
            {
                message = "No permanent address found!" ;
                httpStatus = HttpStatus.BAD_REQUEST ;
            }
            else
            {

             //   message =   addressService.getPermAddress(userId) ;
                return new ResponseEntity<>( addressService.getPermAddress(userId) , HttpStatus.OK);

            }


        }


        return new ResponseEntity<>(message , httpStatus);
    }




    /*
     This is used to get details of all states.
     */
    @GetMapping("/states")
    @CrossOrigin
    public ResponseEntity<?> getAllPermanentAddress() {
        return new ResponseEntity<>( addressService.getAllStates() , HttpStatus.OK);
    }

}
