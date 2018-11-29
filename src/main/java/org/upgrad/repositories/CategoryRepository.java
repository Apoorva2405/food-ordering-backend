package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    // Get all category details.
    @Query(nativeQuery = true,value = "SELECT * FROM CATEGORY")
    Iterable<Category> getCategories();

    // Get category details by name.
    @Query(nativeQuery = true,value = "SELECT * FROM CATEGORY WHERE CATEGORY_NAME=?1")
    Category getCategoryByName(String categoryName);

    // Get category count by name.
    @Query(nativeQuery = true,value = "SELECT count(*) FROM CATEGORY WHERE CATEGORY_NAME=?1")
    Integer getCategoryCountByName(String categoryName);

}
