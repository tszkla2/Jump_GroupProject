package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Product;

public interface ProductDao {
	
	public List<Product> getAllProducts();
	
	public Product getProductById(int id);
	
	public boolean addProduct(Product product);
	
	public boolean deleteProduct(int id);
	
	public boolean updateProduct(Product product);
	
	

}
