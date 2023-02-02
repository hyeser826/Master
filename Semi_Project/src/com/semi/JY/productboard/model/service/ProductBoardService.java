package com.semi.JY.productboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.JDBCTemplate;
import com.semi.product.model.vo.Attachment;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.productboard.model.dao.ProductBoardDao;
import com.semi.JY.productboard.model.vo.ProductBoard;

public class ProductBoardService {


			//아래는 장인의 1:1문의  리스트 (productboard)조회하기 . 
			public ArrayList<ProductBoard> masterinquired(Product p,ProductBoard pb,int memNo) {
				Connection conn=JDBCTemplate.getConnection();
				
				ArrayList<ProductBoard> list=new ProductBoardDao().masterinquired(conn,p,pb,memNo);
				
				JDBCTemplate.close(conn);
				
				
				return list;
			}
			
			//ProductBoard 리스트조회
			public ProductBoard selectProductBoard(int memNo) {
				Connection conn=JDBCTemplate.getConnection();
				
				ProductBoard pb=new ProductBoardDao().selectProductBoard(conn,memNo);
				
				JDBCTemplate.close(conn);
				
				return pb;
			}



}
