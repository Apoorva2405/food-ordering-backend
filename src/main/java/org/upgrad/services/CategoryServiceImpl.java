package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.repositories.CategoryRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Category> getAllCategories() {

        return categoryRepository.getCategories();
    }

    @Override
    public Category getCategory(String categoryName){

        // Case insenstive Check
        String s1 = categoryName.substring(0, 1).toUpperCase();
        String s2 = "null";
        String nameCapitalized = "null";
        int index = 0;
        if (categoryName.contains(" ")){
            index = categoryName.indexOf(" ");
            s2 = categoryName.substring(index+1,index+2).toUpperCase();
        }

        if (s2.equalsIgnoreCase("null")) {

             nameCapitalized = s1 + categoryName.substring(1).toLowerCase();
        }
        else {

             nameCapitalized = s1 + categoryName.substring(1,index+1).toLowerCase() + s2 + categoryName.substring(index+2).toLowerCase();
        }

        return categoryRepository.getCategoryByName(nameCapitalized);
}

    @Override
    public Integer getCategoryCount(String categoryName){

        // Case insenstive Check
        String s1 = categoryName.substring(0, 1).toUpperCase();
        String nameCapitalized = s1 + categoryName.substring(1).toLowerCase();

        return categoryRepository.getCategoryCountByName(nameCapitalized);
    }

}
