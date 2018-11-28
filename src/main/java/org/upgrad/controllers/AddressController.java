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
                    String type1 = "Temp" ;
                    // Save data in address table.
                    Address address = new Address(addressId ,flatBuildingNumber, locality, city, zip, state_id);
                    if ( addressService.addAddress(address)  == 1 )
                    {
                        if ( type != "null" )
                            type1 = type ;
                        addressService.addUserAddress(type1, userId, addressId) ;
                    }

                    System.out.println("address" +  addressId) ;



                    message ="Success" ;
                    httpStatus = HttpStatus.CREATED ;
                }
            } else {
                message = "Invalid zip code!" ;
                httpStatus = HttpStatus.BAD_REQUEST ;
            }
        }
        return new ResponseEntity<>(message , httpStatus);
    }



}
