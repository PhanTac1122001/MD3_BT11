package com.example.ss11b2.sevice;

import com.example.ss11b2.entity.Category;

public interface ICategorySevice {
    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    void deleteById(int id);
    Category findById(int id);
    int getNewId();
}
