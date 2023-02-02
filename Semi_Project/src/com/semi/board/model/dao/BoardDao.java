package com.semi.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.semi.board.model.vo.Board;
import com.semi.common.JDBCTemplate;
import com.semi.common.model.vo.PageInfo;

public class BoardDao {
	
	private Properties prop = new Properties();

	public BoardDao() {
		
		try {
			prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/db/sql/board-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//1:1문의글 총 갯수 조회해오기
	public int selectListCount(Connection conn) {
		//select문 
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				//조회된 게시글 개수
				listCount = rset.getInt("COUNT");
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
	
	//QNA 총 갯수 조회해오기
		public int selectQnaCount(Connection conn, String memId) {
			//select문 
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectQnaCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					//조회된 게시글 개수
					listCount = rset.getInt("COUNT");
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
	
	//페이징바
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		
		//select 문 
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectList");

		
		int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage() * pi.getBoardLimit();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("BOARD_NO")
								  ,rset.getString("MEM_ID")
								  ,rset.getString("BOARD_TITLE")
								  ,rset.getString("BOARD_CONTENT")
								  ,rset.getString("BOARD_ANSWER")
								  ,rset.getDate("BOARD_DATE")
								  ,rset.getDate("ANSWER_DATE")
								  ,rset.getString("BOARD_CATEGORY")
								  ,rset.getString("STATUS")));
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

	
	//페이징바
			public ArrayList<Board> selectQnaList(Connection conn, PageInfo pi, String memId) {
				
				//select 문 
				ResultSet rset = null;
				ArrayList<Board> list = new ArrayList<>();
				PreparedStatement pstmt = null;
				
				String sql = prop.getProperty("selectQnaList");

				
				int startRow = (pi.getCurrentPage()-1) * pi.getBoardLimit()+1;
				int endRow = pi.getCurrentPage() * pi.getBoardLimit();
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, memId);
					pstmt.setInt(2, startRow);
					pstmt.setInt(3, endRow);
					
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						
						list.add(new Board(rset.getInt("BOARD_NO")
										  ,rset.getString("MEM_ID")
										  ,rset.getString("BOARD_TITLE")
										  ,rset.getString("BOARD_CONTENT")
										  ,rset.getString("BOARD_ANSWER")
										  ,rset.getDate("BOARD_DATE")
										  ,rset.getDate("ANSWER_DATE")
										  ,rset.getString("BOARD_CATEGORY")
										  ,rset.getInt("PRODUCT_NO")
										  ,rset.getString("STATUS")));
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

	
	//입점문의 추가
	public int insertLvBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLvBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//카테고리와 작성자는 데이터 추가할때는 NUMBER타입이기 때문에 형변환 
			pstmt.setString(1, b.getMemId());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardCategory());
			pstmt.setString(5, b.getStrName());
			pstmt.setString(6, b.getMasterIntro());
			pstmt.setString(7, b.getCoName());
			pstmt.setString(8, b.getCoNumber());
			pstmt.setString(9, b.getCeo());
			pstmt.setString(10, b.getCoKind());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
		
	}
	
	//일반문의 추가 
	public int insertPsnBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertPsnBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//카테고리와 작성자는 데이터 추가할때는 NUMBER타입이기 때문에 형변환 
			pstmt.setString(1, b.getMemId());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
	
			
			result = pstmt.executeUpdate();
			System.out.println("result;"+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
		
	}
	
	//QNA문의 추가
	public int insertQnaBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQnaBoard");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			//카테고리와 작성자는 데이터 추가할때는 NUMBER타입이기 때문에 형변환 
			pstmt.setString(1, b.getMemId());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getStrName());
			pstmt.setInt(5, b.getProductNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
			
		}
	
	//1:1 & QNA 선택 조회 메소드
	public Board selectBoard(Connection conn, int bno) {
		Board b = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("BOARD_NO")
							 ,rset.getString("BOARD_CATEGORY")
							 ,rset.getString("BOARD_TITLE")
							 ,rset.getString("BOARD_CONTENT")
							 ,rset.getString("BOARD_ANSWER")
							 ,rset.getString("MEM_ID")
							 ,rset.getString("CO_NAME")
							 ,rset.getString("CO_NUMBER")
							 ,rset.getString("CEO")
							 ,rset.getString("CO_KIND")
							 ,rset.getString("STR_NAME")
							 ,rset.getString("MASTER_INTRO")
							 ,rset.getDate("BOARD_DATE")
							 ,rset.getDate("ANSWER_DATE")
							 ,rset.getString("STATUS"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return b;
	}

	
	//1:1 & QNA 선택 조회 메소드
	public Board selectQnaList(Connection conn, int bno) {
		Board b = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board(rset.getInt("BOARD_NO")
							 ,rset.getString("BOARD_CATEGORY")
							 ,rset.getString("BOARD_TITLE")
							 ,rset.getString("BOARD_CONTENT")
							 ,rset.getString("BOARD_ANSWER")
							 ,rset.getString("MEM_ID")
							 ,rset.getString("STR_NAME")
							 ,rset.getDate("BOARD_DATE")
							 ,rset.getDate("ANSWER_DATE")
							 ,rset.getString("STATUS")
							 ,rset.getInt("PRODUCT_NO"));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return b;
	}

	//1:1문의 QNA 수정 메소드
	public int updateBoard(Connection conn, Board b) {
		
		//update
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getCoName());
			pstmt.setString(4, b.getCoNumber());
			pstmt.setString(5, b.getCeo());
			pstmt.setString(6, b.getCoKind());
			pstmt.setString(7, b.getStrName());
			pstmt.setString(8, b.getMasterIntro());
			pstmt.setInt(9, b.getBoardNo());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	//관리자 답변 및 답변 수정
	public int updateAnswer(Connection conn, int bno, String dearPsn) {
		
		//update
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAnswer");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dearPsn);
			pstmt.setInt(2, bno);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	//작성글 삭제
	public int deleteBoard(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Integer> checkMaster(Connection conn, String chkMaster) {
		
		//select 문 
		ResultSet rset = null;
		ArrayList<Integer> pnoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("checkMaster");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, chkMaster);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				pnoList.add(rset.getInt("PRODUCT_NO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return pnoList;
	}

	//에이젝스 상품문의글 리스트 조회 메소드
	public ArrayList<Board> selectProductQnAList(Connection conn, int pno) {
		
		ResultSet rset = null;
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectProductQnAList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("BOARD_NO")
						,rset.getString("MEM_NAME")
						,rset.getString("BOARD_TITLE")
						,rset.getString("BOARD_CONTENT")
						,rset.getString("BOARD_ANSWER")
						,rset.getDate("BOARD_DATE")
						,rset.getDate("ANSWER_DATE")
						,rset.getString("BOARD_CATEGORY")
						,rset.getInt("PRODUCT_NO")
						
						));
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

	

}
