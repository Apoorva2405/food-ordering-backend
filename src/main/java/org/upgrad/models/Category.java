package org.upgrad.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
    @Id
    private int id;

    @Column(name = "category_name")
    private String categoryName;

    public String getCategory_name() {
        return categoryName;
    }

    public void setCategoryName(String category_name) {
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
