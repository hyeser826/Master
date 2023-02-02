package com.semi.product.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.semi.common.JDBCTemplate;
import com.semi.product.model.dao.MasterDao;
import com.semi.product.model.dao.ProductDao;
import com.semi.product.model.vo.Master;
import com.semi.product.model.vo.Product;

public class MasterService {

	public int masterNumber(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		int masterNo = new MasterDao().masterNumber(conn,memNo); 
		
		JDBCTemplate.close(conn);
		return masterNo;
	}

}
