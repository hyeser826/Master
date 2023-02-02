package com.semi.JY.productboard.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.product.model.vo.Attachment;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.productboard.model.vo.ProductBoard;

public class ProductBoardDao {
	Properties prop = new Properties();

	public ProductBoardDao() {
			String filePath = ProductBoardDao.class.getResource("/db/sql/productboard-mapper.xml").getPath();
			
			try {
				prop.loadFromXML(new FileInputStream(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("productboard-mapper.xml 경로이상");
			}
		}

		public int insertProductBoard(ProductBoard pb, Connection conn) {
		int	result = 0;
		PreparedStatement pstmt = null;
		
		String sql =prop.getProperty("insertProductBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			//내가 필요한건 ..    상품번호,회원번호 , , 문의내용 ,작성날짜 ,게시글상태
			pstmt.setInt(1, pb.getMemNo());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
			
		}

		//아래는 장인의 1:1문의 게시판  ProductBoard 리스트 조회할꺼임 
		public ArrayList<ProductBoard> masterinquired(Connection conn,Product p,ProductBoard pb,int memNo) {
			
			PreparedStatement pstmt =null;
			ArrayList<ProductBoard> list = new ArrayList<>();
			ResultSet rset =null; 
										//장인번호로 조회
			String sql = prop.getProperty("masterinquired");
			
			try {
				pstmt=conn.prepareStatement(sql);
							//장인번호로 조회
//				pstmt.setInt(1,p.getMasterNo());
				pstmt.setInt(1,pb.getProductBoardNo());
//				pstmt.setInt(2,p.getProductNo());
				pstmt.setInt(2,p.getMasterNo());
				
				rset=pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new ProductBoard(	rset.getInt("PRODUCT_BOARD_NO") //문의번호
												,rset.getInt("PRODUCT_NO") //상품번호
												,rset.getInt("MEM_NO") //회원번호
												,rset.getString("PRODUCT_BOARD_CONTENT") //문의내용
												,rset.getDate("PRODUCT_BOARD_DATE")//작성날짜
												,rset.getString("STATUS") //처리상태
									));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			System.out.println("ProductBoard> masterinquired 안담겻어?"+list);
			return list;
		}
		
		//ProductBoard 내가 작성한 리뷰 하기위해 리스트조회
		public ProductBoard selectProductBoard(Connection conn,int memNo) {
			
			PreparedStatement pstmt =null;
			ResultSet rset =null;
			ProductBoard pb = new ProductBoard();
			
				String sql = prop.getProperty("selectProductBoard");
			
			try {
				pstmt=conn.prepareStatement(sql);
				 
				pstmt.setInt(1,memNo);
				
				rset=pstmt.executeQuery();
				
				if(rset.next()) {
					pb=new ProductBoard(		rset.getInt("PRODUCT_BOARD_NO") //문의번호
												,rset.getInt("PRODUCT_NO") //상품번호
												,rset.getInt("MEM_NO") //회원번호
												,rset.getString("PRODUCT_BOARD_CONTENT") //문의내용
												,rset.getDate("PRODUCT_BOARD_DATE")//작성날짜
												,rset.getString("STATUS") //처리상태
									);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			System.out.println("selectProductBoard 리스트조회"+pb);
			return pb;
		}
		
	

}
