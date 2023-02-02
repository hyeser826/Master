package com.semi.JY.product.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.productboard.model.dao.ProductBoardDao;
import com.semi.JY.productboard.model.vo.ProductBoard;

public class ProductDao {

	Properties prop = new Properties();
	
	public ProductDao() {
		String filePath = ProductDao.class.getResource("/db/sql/product-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("product-mapper.xml 경로이상");
		}
	}
			//product  list
	public ArrayList<Product> productList(Connection conn) {
		ResultSet rset =null;
		PreparedStatement pstmt = null;
		ArrayList<Product>list = new ArrayList<>();
		String sql = prop.getProperty("productList");
		
		try {
			pstmt=conn.prepareStatement(sql);
		
			rset=pstmt.executeQuery();
		
			while(rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
								,rset.getString("CATEGORY_NAME")
								,rset.getInt("MASTER_NO")
								,rset.getString("PRODUCT_NAME")
								,rset.getInt("PRODUCT_PRICE")
								,rset.getString("PRODUCT_DESCRIPTION")
								,rset.getDate("PRODUCT_DATE")
								,rset.getInt("PRODUCT_STOCK")
								,rset.getInt("PRODUCT_HITS")
								,rset.getString("STATUS")
								,rset.getInt("DELIVERY_FEE")
								,rset.getString("EXP_PERIOD"))
				
		);
			}

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);

			JDBCTemplate.close(pstmt);
		}
		return list;  
	}
	
	
	//product select
	public Product selectproductList(Connection conn) {
		
		ResultSet rset =null;
		PreparedStatement pstmt = null;
		Product p = new Product();
		
		String sql = prop.getProperty("selectproductList");
		try {
			pstmt=conn.prepareStatement(sql);
			rset=pstmt.executeQuery();
		
			while(rset.next()) {
				p=(new Product(rset.getInt("PRODUCT_NO")
								,rset.getString("CATEGORY_NAME")
								,rset.getInt("MASTER_NO")
								,rset.getString("PRODUCT_NAME")
								,rset.getInt("PRODUCT_PRICE")
								,rset.getString("PRODUCT_DESCRIPTION")
								,rset.getDate("PRODUCT_DATE")
								,rset.getInt("PRODUCT_STOCK")
								,rset.getInt("PRODUCT_HITS")
								,rset.getString("STATUS")
								,rset.getInt("DELIVERY_FEE")
								,rset.getString("EXP_PERIOD"))
				
		);
			}

		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);

			JDBCTemplate.close(pstmt);
		}
		return p;  
		
	}

}
