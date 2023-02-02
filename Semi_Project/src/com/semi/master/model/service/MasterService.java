package com.semi.master.model.service;

import java.sql.Connection;

import com.semi.common.JDBCTemplate;
import com.semi.master.model.dao.MasterDao;
import com.semi.master.model.vo.Master;

public class MasterService {

	public int masterNumber(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int masterNo = new MasterDao().masterNumber(conn,memNo); 
		
		JDBCTemplate.close(conn);
		return masterNo;
	}

	public Master selectMst(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Master m = new MasterDao().selectMst(conn,memNo); 
		
		JDBCTemplate.close(conn);
		
		return m;
	}

	public int updateMasterIntro(Master m) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MasterDao().updateMasterIntro(conn,m);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}

}
