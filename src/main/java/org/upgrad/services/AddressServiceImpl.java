package org.upgrad.services;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.upgrad.models.Address;
import org.upgrad.models.Restaurant;
import org.upgrad.models.States;
import org.upgrad.repositories.StateRepository;
import org.upgrad.repositories.AddressRepository;
import org.upgrad.models.UserAddress;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.UserPremAddressResponse;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        return addressRepository.addAddress(address.getFlat_buil_number(), address.getLocality(), address.getCity() , address.getZipcode() , address.getState().getId());
    }

    // This method is used to check whether the user is logged in or not.
    @Override
    public States isValidState(Integer id) {
        System.out.println("State id" + id);
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

    @Override
    public Iterable<States> getAllStates() {
        return stateRepository.getAllStates();
    }

    @Override
    public Address getaddressById( Integer addressId) { return addressRepository.findAddressById(addressId) ;}

    @Override
    public Integer updateAddressById (String flat_build_num , String locality, String city, String zipcode , Integer state_id , Integer id)
    {
        return addressRepository.updateAddressById(flat_build_num,locality,city,zipcode,state_id,id);
    }

    @Override
    public Integer deleteAddressById (Integer id )
    {
        return addressRepository.deleteAddressById(id);
    }

    @Override
    public Integer deleteUserAddressById(Integer id) {
        return addressRepository.deleteUserAddressById(id);
    }

    @Override
    public Boolean getAddress(Integer addressId){

        if (addressRepository.findAddressById(addressId) == null )
            return false;
        else
            return true ;
    }

    @Override
    public  Iterable<UserPremAddressResponse>  getPermAddress(Integer userId)
    {
        List<UserPremAddressResponse> userList = new ArrayList<>();

        Iterable<Integer> premAddressIdList = addressRepository.getPermAdd(userId);

        if( premAddressIdList.iterator().hasNext() )
        {
            for (Integer addressId: premAddressIdList ) {
                Address  add = addressRepository.findAddressById(addressId) ;
                States state = stateRepository.getStatebyId(add.getState().getId());


                UserPremAddressResponse resp = new UserPremAddressResponse(add.getId(), add.getFlat_buil_number(), add.getLocality(), add.getCity(), add.getZipcode(), state);
                userList.add(resp);
            }
        }

    /*    List<RestaurantResponse> restaurants = new ArrayList<>();
        Iterable<Restaurant> restaurantList = restaurantRepository.getRestaurantsByRestName(name);

        if (restaurantList.iterator().hasNext()) {
            for (Restaurant restaurant: restaurantList) {
                List<Integer> catIds = (List<Integer>) restaurantRepository.getCategoryId(restaurant.getId());
                List<String> categoryList = new ArrayList<>();
                if (catIds.size()!=0) {
                    for (Integer catid: catIds) {
                        String restCategory = categoryRepository.getCategoryNameById(catid);
                        categoryList.add(restCategory);
                    }
                }
                RestaurantResponse resp = new RestaurantResponse(restaurant.getId(),restaurant.getRestaurantName(),restaurant.getPhotoUrl(),restaurant.getUserRating(),restaurant.getAvgPrice(),restaurant.getNumberUsersRated(),restaurant.getAddress(),categoryList.toString());
                restaurants.add(resp);
            }
        }
        return restaurants; */
        return userList;
    }
}