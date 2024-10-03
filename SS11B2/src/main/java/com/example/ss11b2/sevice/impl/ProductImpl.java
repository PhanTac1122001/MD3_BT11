package com.example.ss11b2.sevice.impl;

import com.example.ss11b2.entity.Category;
import com.example.ss11b2.entity.Product;
import com.example.ss11b2.sevice.IProductSevice;

import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements IProductSevice {
    public static List<Product> products=new ArrayList<>();

    @Override
    public boolean addProduct(Product product) {
        product.setId(getNewId());
        products.add(product);
        return true;
    }

    @Override
    public boolean updateCategory(Product product) {
        products.set(findIndexById(product.getId()),product);
        return true;
    }
    public int findIndexById(int id){
        for (int i=0;i<products.size();i++){
            if(products.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void deleteById(int id) {
        int indexDelete=findIndexById(id);
        products.remove(indexDelete);
    }

    @Override
    public Product findById(int id) {
        if(findIndexById(id) !=-1){
            return products.get(findIndexById(id));
        }
        return null;
    }


    @Override
    public int getNewId() {
        int maxId=0;
        for(Product p:products){
            if(maxId<p.getId()){
                maxId=p.getId();
            }
        }
        return maxId+1;
    }
}
