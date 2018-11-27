package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.States;
import org.upgrad.models.UserAuthToken;

@Repository
public interface StateRepository extends CrudRepository<UserAuthToken, Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM STATES WHERE id=?1")
    States isValidState(Integer id);

}
