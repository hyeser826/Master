package com.semi.product.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.semi.common.JDBCTemplate;
import com.semi.product.model.vo.Master;
import com.semi.product.model.vo.Product;

public class MasterDao {
	private Properties prop = new Properties();
	
	public MasterDao() {
		try {
			prop.loadFromXML(new FileInputStream(ProductDao.class.getResource("/db/sql/master-mapper.xml").getPath()));
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
}
