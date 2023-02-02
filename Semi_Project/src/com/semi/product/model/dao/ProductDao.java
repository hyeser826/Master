package com.semi.product.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.master.model.vo.Master;
import com.semi.product.model.vo.Attachment;
import com.semi.product.model.vo.Product;

public class ProductDao {

	private Properties prop = new Properties();

	public ProductDao() {

		try {
			prop.loadFromXML(new FileInputStream(ProductDao.class.getResource("/db/sql/product-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//상품목록 조회 메소드
		public ArrayList<Product> selectProductList(Connection conn) {
			ArrayList<Product> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectProductList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Product(rset.getInt("PRODUCT_NO"),
									   rset.getString("PRODUCT_NAME"),
									   rset.getInt("PRODUCT_PRICE"))
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

		//조회수 증가 메소드
		public int increaseCount(Connection conn, int pno) {

			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("increaseCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		//상품 한개 조회 메소드
		public Product selectProduct(Connection conn, int pno) {

			Product p = null;
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectProduct");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					p = new Product(rset.getInt("PRODUCT_NO")
							, rset.getString("CATEGORY_NAME")
							, rset.getInt("MASTER_NO")
							, rset.getString("PRODUCT_NAME")
							, rset.getInt("PRODUCT_PRICE")
							, rset.getString("PRODUCT_DESCRIPTION")
							,rset.getDate("PRODUCT_DATE")
							, rset.getInt("PRODUCT_STOCK")
							, rset.getInt("PRODUCT_HITS")
							,rset.getInt("DELIVERY_FEE")
							, rset.getString("EXP_PERIOD")
							, rset.getString("FILE_PATH")
							, rset.getString("STR_NAME"));
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

		public ArrayList<Attachment> selectAttachmentList(Connection conn, int pno) {
			ArrayList<Attachment> list = new ArrayList<>();
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("selectAttachmentList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Attachment(rset.getInt("ATTACHMENT_NO"),
											rset.getInt("PRODUCT_NO"),
											rset.getString("FILE_NAME"),
											rset.getString("SYS_NAME"),
											rset.getString("FILE_PATH")));
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
		
		//상품게시판 상품추가 메소드
		public int insertProduct(Connection conn, Product p) {
			//insert
			int result = 1;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertProduct");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, p.getCategoryName());
				pstmt.setInt(2, p.getMstNo());
				pstmt.setString(3, p.getProName());
				pstmt.setInt(4, p.getProPrice());
				pstmt.setString(5, p.getProDescription());
				pstmt.setInt(6, p.getProStock());
				pstmt.setString(7, p.getProStatus());
				pstmt.setInt(8, p.getDeliveryFee());
				pstmt.setString(9, p.getExpPeriod());

				result *= pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		
		}

		public int insertAttachment(Connection conn, ArrayList<Attachment> list) {

			//insert문 여러번
			int result = 1;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertAttachment");
			
			try {
				
				for(Attachment at: list) {
					//반복문이 돌때마다 미완성 sql문을 담은 pstmt 객체를 생성하고 완성형태로 만들기
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, at.getFileName());
					pstmt.setString(2, at.getSysName());
					pstmt.setString(3, at.getFilePath());
					pstmt.setInt(4, at.getFileLevel());
					
					//실행 후 결과받기
					result *= pstmt.executeUpdate();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
			
			return result;
		}

		//게시글 총 개수 반환 메소드
		public int selectListCount(Connection conn) {
			//select문
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectListCount");
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					//조회된 게시글 수
					listCount = rset.getInt("PRODUCT_HITS");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return listCount;
		}

		
		//선택한 상품의 첨부파일 조회 메소드
		public Attachment selectAttachment(Connection conn, int pno) {
			Attachment at = null;
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectAttachment");
			
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, pno);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					at = new Attachment(rset.getInt("FILE_NO"),
										rset.getString("ORIGIN_NAME"),
										rset.getString("CHANGE_NAME"),
										rset.getString("FILE_PATH"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			
			return at;
		}

		//사용자 요청 페이지 메소드 목록
		//상품 관리페이지에 A장인에 대한 목록 리스트 뿌리기 위한 메소드
		public ArrayList<Product> masterCheck(Connection conn, int memNo) {

			ResultSet rset = null;
			ArrayList<Product> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("masterProductList");
			
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setInt(1, memNo);
				
				rset = pstmt.executeQuery();
				
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
										 ,rset.getString("EXP_PERIOD")
									  )
							);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return list;
		}

		public int updateProduct(Connection conn, Product p) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateProduct");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				/*
				상품명
				카테고리
				가격
				배송비
				상품수량
				상품상세내용
				체험기간
				상품판매여부
				*/
				
				pstmt.setString(1, p.getProName());
				pstmt.setString(2, p.getCategoryName());
				pstmt.setInt(3, p.getProPrice());
				pstmt.setInt(4, p.getDeliveryFee());
				pstmt.setInt(5, p.getProStock());
				pstmt.setString(6, p.getProDescription());
				pstmt.setString(7, p.getProStatus());
				pstmt.setString(8, p.getExpPeriod());
				pstmt.setInt(9, p.getProNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		public int updateAttachment(Connection conn, ArrayList<Attachment> list) {
			//update구문
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateAttachment");
			
			try {
				pstmt= conn.prepareStatement(sql);
				
				for(Attachment at:list) {
					pstmt.setString(1, at.getFileName());
					pstmt.setString(2, at.getSysName());
					pstmt.setString(3, at.getFilePath());
					pstmt.setInt(4, at.getAttachmentNo());
				}
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}
			
		public int insertNewAttachment(Connection conn, ArrayList<Attachment> list) {
			//insert구문
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertNewAttachment");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				for(Attachment at:list) {
					pstmt.setInt(1, at.getProNo());
					pstmt.setString(2, at.getFileName());
					pstmt.setString(3, at.getSysName());
					pstmt.setString(4, at.getFilePath());
				}
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		public int deleteProduct(Connection conn, int pNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteProduct");
			
			try {
				pstmt= conn.prepareStatement(sql);
				
				pstmt.setInt(1, pNo);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		public Master selectMstNo(Connection conn, int memNo) {
			Master m = null;
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectMstNo");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					m = new Master(rset.getInt("MASTER_NO"),				
									rset.getInt("MASTER_NO"),				
									rset.getString("STR_NAME"),
									rset.getString("MASTER_INTRO")
									);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return m;
		}

	public ArrayList<Product> selectTablewearList(Connection conn) {

		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectTablewearList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public ArrayList<Product> selectActivityList(Connection conn) {

		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectActivityList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;

	}

	public ArrayList<Product> selectHouseItemsList(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectHouseItemsList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public ArrayList<Product> selectStationeryList(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectStationeryList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public ArrayList<Product> selectAccList(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectAccList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));
						

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public ArrayList<Product> selectClothList(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectClothList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));
						

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public ArrayList<Product> selectEtcList(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectEtcList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));
						
						

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public ArrayList<Product> selectInteriorList(Connection conn) {
		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectInteriorList");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));
						

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	//에이젝스 낮은 가격순 정렬
	public ArrayList<Product> selectLowPriceList(Connection conn, String categoryName) {
		ArrayList<Product> lowPricelist = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectLowPriceList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				lowPricelist.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return lowPricelist;
	}

	//에이젝스 높은가격순 정렬
	public ArrayList<Product> selectHighPriceList(Connection conn, String categoryName) {
		ArrayList<Product> highPricelist = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectHighPriceList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				highPricelist.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return highPricelist;
	}

	//에이젝스 최근상품순 정렬
	public ArrayList<Product> selectRecentProductList(Connection conn, String categoryName) {
		ArrayList<Product> recentProductList = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectRecentProductList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				recentProductList.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return recentProductList;
	}

	
	//에이젝스 인기순 정렬
	public ArrayList<Product> selectBestProductList(Connection conn, String categoryName) {
		ArrayList<Product> bestProductList = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectBestProductList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				bestProductList.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return bestProductList;
	}

	public ArrayList<Product> selectMasterStoreList(Connection conn, int mno) {
		ArrayList<Product> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectMasterStoreList");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, mno);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Product(rset.getInt("PRODUCT_NO")
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")
						,rset.getString("STR_NAME")));
						

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	
	//에이젝스 장인스토어 인기순 정렬
	public ArrayList<Product> selectMasterStoreBestList(Connection conn, int mno) {
		ArrayList<Product> mbestList = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectMasterStoreBestList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				mbestList.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return mbestList;
	}

	public ArrayList<Product> selectMasterStoreHighPriceList(Connection conn, int mno) {
		ArrayList<Product> mHighPriceList = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectMasterStoreHighPriceList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				mHighPriceList.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return mHighPriceList;
	}

	public ArrayList<Product> selectMasterStoreLowPriceList(Connection conn, int mno) {
		ArrayList<Product> mLowPriceList = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectMasterStoreLowPriceList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				mLowPriceList.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return mLowPriceList;
	}

	public ArrayList<Product> selectMasterStoreRecentProductList(Connection conn, int mno) {
		ArrayList<Product> mRecentList = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("selectMasterStoreRecentProductList");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				mRecentList.add(new Product(rset.getInt("PRODUCT_NO") 
						,rset.getString("CATEGORY_NAME")
						,rset.getInt("MASTER_NO")
						,rset.getString("PRODUCT_NAME")
						,rset.getInt("PRODUCT_PRICE")
						,rset.getDate("PRODUCT_DATE")
						,rset.getInt("PRODUCT_STOCK")
						,rset.getInt("PRODUCT_HITS")
						,rset.getString("FILE_PATH")));

						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return mRecentList;
	}

}
