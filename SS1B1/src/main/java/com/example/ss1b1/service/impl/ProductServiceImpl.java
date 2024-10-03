package com.example.ss1b1.service.impl;

import com.ra.session11md3.entity.Product;
import com.ra.session11md3.service.IProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService
{
	
	public static List<Product> products = new ArrayList<>();
	
	static
	{
		products.add(new Product(1L, "Quần", 1200.0, "mô tả quần", "GUCCI", true));
		products.add(new Product(2L, "Áo", 1500.0, "mô tả áo", "LV", true));
		products.add(new Product(3L, "Dép", 2000.0, "mô tả dép", "Dolce&gabbana", true));
	}
	
	@Override
	public boolean addProduct(Product product)
	{
		product.setId(getNewId());
		products.add(product);
		return true;
	}
	
	
	@Override
	public List<Product> searchProduct(String keyword)
	{
		return products.stream().filter(item -> item.getName().toLowerCase().contains(keyword.toLowerCase())).toList();
	}
	
	@Override
	public boolean updateProduct(Product product)
	{
		products.set(findIndexById(product.getId()), product);
		return true;
	}
	
	@Override
	public void deleteById(Long id)
	{
		int indexDelete = findIndexById(id);
		products.remove(indexDelete);
	}
	
	
	@Override
	public Product findById(Long id)
	{
		if (findIndexById(id) != -1)
		{
			return products.get(findIndexById(id));
		}
		return null;
	}
	
	public int findIndexById(Long id)
	{
		for (int i = 0; i < products.size(); i++)
		{
			if (products.get(i).getId().equals(id))
			{
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public Long getNewId()
	{
		Long maxId = 0L;
		for (Product p : products)
		{
			if (maxId < p.getId())
			{
				maxId = p.getId();
			}
		}
		return maxId + 1;
	}
}
