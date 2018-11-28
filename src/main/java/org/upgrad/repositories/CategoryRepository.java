package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    @Query(nativeQuery = true,value="SELECT * FROM CATEGORY WHERE category_name=?1")
    Category findCategoryByName(String name);
}
