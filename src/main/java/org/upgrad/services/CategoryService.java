package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Category;

@Service
public interface CategoryService {

    Iterable<Category> getAllCategories();

    Category getCategoryByName(String name);

}
