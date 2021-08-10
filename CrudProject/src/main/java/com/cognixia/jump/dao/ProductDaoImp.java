package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Product;

public class ProductDaoImp implements ProductDao {
	
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static String SELECT_ALL_PRODUCTS = "select * from product";
	private static String SELECT_PRODUCT_BY_ID = "select * from product where id = ?";
	private static String INSERT_PRODUCT = "insert into product(item, qty, description) values(?, ?, ?)";
	private static String DELETE_PRODUCT = "delete from product where id = ?";
	private static String UPDATE_PRODUCT = "update product set item = ?, qty = ?, description = ? where id = ?";

	@Override
	public List<Product> getAllProducts() {
		
		List<Product> allProducts = new ArrayList<Product>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PRODUCTS);
				ResultSet rs = pstmt.executeQuery() ) {
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String item = rs.getString("item");
				int qty = rs.getInt("qty");
				String description = rs.getString("description");
				
				allProducts.add(new Product(id, item, qty, description));
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allProducts;
	}

	@Override
	public Product getProductById(int id) {
		
		Product product = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PRODUCT_BY_ID)) {
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			// if product found, if statement run, if not null returned as product
			if(rs.next()) {
				String item = rs.getString("item");
				int qty = rs.getInt("qty");
				String description = rs.getString("description");
				
				product = new Product(id, item, qty, description);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return product;
	}

	@Override
	public boolean addProduct(Product product) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_PRODUCT)) {
			
			pstmt.setString(1, product.getItem());
			pstmt.setInt(2, product.getQty());
			pstmt.setString(3, product.getDescription());
			
			// at least one row added
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteProduct(int id) {

		try (PreparedStatement pstmt = conn.prepareStatement(DELETE_PRODUCT)) {

			pstmt.setInt(1, id);

			// at least one row deleted
			if (pstmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_PRODUCT)) {

			pstmt.setString(1, product.getItem());
			pstmt.setInt(2, product.getQty());
			pstmt.setString(3, product.getDescription());
			pstmt.setInt(4, product.getId());

			// at least one row updated
			if (pstmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
