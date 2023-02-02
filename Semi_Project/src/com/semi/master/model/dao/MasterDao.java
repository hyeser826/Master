package com.semi.master.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.master.model.vo.Master;
import com.semi.product.model.vo.Product;

public class MasterDao {
	private Properties prop = new Properties();
	
	public MasterDao() {
		try {
			prop.loadFromXML(new FileInputStream(MasterDao.class.getResource("/db/sql/master-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int masterNumber(Connection conn, int memNo) {
		int masterNo = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("masterNumber");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				masterNo = rset.getInt("MASTER_NO");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
			
		return masterNo;
	}

	public Master selectMst(Connection conn, int memNo) {
		
		Master m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMst");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Master(rset.getInt("MASTER_NO")
							  ,rset.getInt("MEM_NO")
							  ,rset.getString("STR_NAME")
							  ,rset.getString("MASTER_INTRO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return m;
	}

	public int updateMasterIntro(Connection conn, Master m) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMasterIntro");
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMasterIntro());
			pstmt.setInt(2, m.getMasterNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
}
