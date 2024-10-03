package com.example.ss11b2.sevice;


import com.example.ss11b2.entity.Product;

public interface IProductSevice {
    boolean addProduct(Product product);

    boolean updateCategory(Product product);

    void deleteById(int id);
    Product findById(int id);
    int getNewId();
}
