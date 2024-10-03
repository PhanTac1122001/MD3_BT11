package com.example.ss11b2.sevice.impl;

import com.example.ss11b2.entity.Category;
import com.example.ss11b2.sevice.ICategorySevice;

import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements ICategorySevice {
    public static List<Category> categories =new ArrayList<>();
    static
    {
        categories.add(new Category(1, "Quần", true));
        categories.add(new Category(2, "Áo", true));
        categories.add(new Category(3, "Dép", false));
    }

    @Override
    public boolean addCategory(Category category) {
        category.setId(getNewId());
        categories.add(category);
        return true;
    }

    @Override
    public boolean updateCategory(Category category) {
        categories.set(findIndexById(category.getId()),category);
        return true;
    }

    @Override
    public void deleteById(int id) {
        int indexDelete=findIndexById(id);
        categories.remove(indexDelete);
    }

    @Override
    public Category findById(int id) {
        if(findIndexById(id) !=-1){
            return categories.get(findIndexById(id));
        }
        return null;
    }

    public int findIndexById(int id){
        for (int i=0;i<categories.size();i++){
            if(categories.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getNewId() {
        int maxId=0;
        for(Category c:categories){
            if(maxId<c.getId()){
                maxId=c.getId();
            }
        }
        return maxId+1;
    }
}
