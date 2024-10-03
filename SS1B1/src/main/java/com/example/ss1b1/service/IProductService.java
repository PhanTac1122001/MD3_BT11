package com.example.ss1b1.service;

import com.ra.session11md3.entity.Product;

import java.util.List;

public interface IProductService
{
	boolean addProduct(Product product);
	
	void deleteById(Long id);
	
	Product findById(Long id);
	
	boolean updateProduct(Product product);
	
	List<Product> searchProduct(String keyword);
	
	Long getNewId();
}
