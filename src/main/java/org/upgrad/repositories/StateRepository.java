package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Restaurant;
import org.upgrad.models.States;

@Repository
public interface StateRepository extends CrudRepository<States, Integer> {

    /*
        This selects state Name for the state_id.
     */
    @Query(nativeQuery = true,value = "SELECT state_name FROM STATES WHERE id=?1")
    String isValidState(Integer id);


}
