package com.semi.JY.product.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.JDBCTemplate;
import com.semi.JY.product.model.dao.ProductDao;
import com.semi.JY.product.model.vo.Product;

public class ProductService {

	public ArrayList<Product> productList() {
		//상품 리스트
		Connection conn=JDBCTemplate.getConnection();
		
		ArrayList<Product>list =new ProductDao().productList(conn);
		
		JDBCTemplate.close(conn);
		return list;
	}
	
	//select product
	public Product selectproductList() {
		Connection conn=JDBCTemplate.getConnection();
		
		Product p=new ProductDao().selectproductList(conn);
		JDBCTemplate.close(conn);
		return p;
	}

}
