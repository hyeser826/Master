package com.semi.product.model.service;

import java.sql.Connection;
import java.util.ArrayList;


import com.semi.common.JDBCTemplate;
import com.semi.master.model.vo.Master;
import com.semi.product.model.vo.Attachment;
import com.semi.product.model.vo.Product;
import com.semi.product.model.dao.ProductDao;

public class ProductService {
	public ArrayList<Product> selectProductList() {

		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().selectProductList(conn);
		
		return list;
	}

	public int increaseCount(int pno) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().increaseCount(conn,pno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}

	public Product selectProduct(int pno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Product p = new ProductDao().selectProduct(conn,pno);
		
		JDBCTemplate.commit(conn);
		
		return p;
	}

	public ArrayList<Attachment> selectaAttachmentList(int pno) {

		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Attachment> list = new ProductDao().selectAttachmentList(conn, pno);
		
		return list;
	}

	public int insertProduct(Product p, ArrayList<Attachment> list) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().insertProduct(conn,p);
		int result2 = new ProductDao().insertAttachment(conn,list);
		
		int finalResult = result*result2;
		
		if(finalResult>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return finalResult;
	}

	public int selectListCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new ProductDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

	//첨부파일가져올 리스트
	public ArrayList<Attachment> selectAttachmentList(int pno) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Attachment> list = new ProductDao().selectAttachmentList(conn,pno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//a장인의 대한 상품들 리스트 가져오기
	public ArrayList<Product> masterCheck(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ProductDao().masterCheck(conn,memNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int updateProduct(Product p, ArrayList<Attachment> list) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().updateProduct(conn,p);
		int result2 = 1;
		
		//새롭게 첨부된 파일이 있는 경우
		for(int i=0;i<list.size();i++) {
			if(list != null) {
				if(list.get(i).getAttachmentNo()!=0) {	//기존 첨부파일이 있었던 경우 - update
					result2 = new ProductDao().updateAttachment(conn,list);
				}else {	//기존 처부파일이 없었던 경우 - insert
					//기존 insertAttachment를 쓸 수 없는 이유 
					//: 기존에는 참조 게시글 번호를 게시글이 등록됨과 동시에 currentValue를 잡아줬기 때문에
					//	이미 뽑혀있는 시퀀스 번호를 찾으려면 전달받아서 진행해야 한다. 
					result2 = new ProductDao().insertNewAttachment(conn,list);
				}
			}
		}
		//게시글 정보 수정과 첨부파일 정보 수정이 둘 다 올바르게 되었을 때를 알 수 있게 곱연산 해줌.
		//둘 중 하나라도 실패하여 0이 나오면 result가 0이 되게끔.
		int finalResult = result*result2;
		
		if(finalResult>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return finalResult;
	}

	public int deleteBoard(int pNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new ProductDao().deleteProduct(conn,pNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Master selectMstNo(int memNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Master m = new ProductDao().selectMstNo(conn,memNo);
		
		JDBCTemplate.close(conn);
		
		return m;
	}

	public ArrayList<Product> selectTablewearList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectTablewearList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Product> selectActivityList() {

		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectActivityList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
		
		
	}

	public ArrayList<Product> selectHouseItemsList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectHouseItemsList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Product> selectStationeryList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectStationeryList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Product> selectAccList() {
	Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectAccList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Product> selectClothList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectClothList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Product> selectEtcList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectEtcList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Product> selectInteriorList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectInteriorList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//에이젝스 낮은 가격순 정렬 
	public ArrayList<Product> selectLowPriceList(String categoryName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectLowPriceList(conn,categoryName);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//에이젝스 높은 가격순 정렬
	public ArrayList<Product> selectHighPriceList(String categoryName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectHighPriceList(conn,categoryName);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//에이젝스 최신순 정렬
	public ArrayList<Product> selectRecentProductList(String categoryName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectRecentProductList(conn,categoryName);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	
	//에이젝스 인기순 정렬
	public ArrayList<Product> selectBestProductList(String categoryName) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectBestProductList(conn,categoryName);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//장인스토어리스트
	public ArrayList<Product> selectMasterStoreList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectMasterStoreList(conn,mno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//에이젝스 장인스토어 인기순 정렬
	public ArrayList<Product> selectMasterStoreBestList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectMasterStoreBestList(conn,mno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//에이젝스 장인스토어 높은가격순 정렬
	public ArrayList<Product> selectMasterStoreHighPriceList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectMasterStoreHighPriceList(conn,mno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//에이젝스 장인스토어 낮은가격순 정렬
	public ArrayList<Product> selectMasterStoreLowPriceList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectMasterStoreLowPriceList(conn,mno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//에이젝스 장인스토어 최근상품순 정렬
	public ArrayList<Product> selectMasterStoreRecentProductList(int mno) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Product> list = new ProductDao().selectMasterStoreRecentProductList(conn,mno);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	

	

}
