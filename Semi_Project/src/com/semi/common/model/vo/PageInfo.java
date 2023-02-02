package com.semi.common.model.vo;

import com.semi.board.model.service.BoardService;

public class PageInfo {

	private int listCount; //현재 총 게시물 개수
	private int currentPage; //현재페이지(사용자가 요청한 페이지)
	private int pageLimit; //페이지 하단에 보여질 베이징바의 최대 개수
	private int boardLimit; //한 페이지에 보여질 게시글의 최대개수(몇개 단위씩 보여질건지)
	
	private int maxPage; //가장 마지막 페이지가 몇번페이지 인지(총 페이지수)
	private int startPage; //페이지 하단에 보여질 페이징바의 시작수
	private int endPage; //페이지 하단에 보여질 페이징바의 끝수
	
	public PageInfo() {
		super();
	}
	
	public PageInfo(int currentPage) {
		//listCount : 현재 총 게시물 개수
		listCount = new BoardService().selectListCount();
		
		//currentPage : 현재페이지(사용자가 요청한 페이지)
		this.currentPage = currentPage;
		
		//pageLimit : 페이지 하단에 보여질 베이징바의 최대 개수
		pageLimit = 10;
		
		//boardLimit : 한 페이지에 보여질 게시글의 최대개수(몇개 단위씩 보여질건지)
		boardLimit = 10;
		
	}
	
	public PageInfo(int listCount, int currentPage, int pageLimit, int boardLimit) {
		//listCount : 현재 총 게시글의 개수
		this.listCount = listCount;
		
		//currentPage : 현재 페이지(사용자가 요청한 페이지)
		this.currentPage = currentPage;
		
		//pageLimit : 페이지 하단에 보여질 페이징 바 최대 개수
		this.pageLimit = pageLimit;
		
		//boardLimit : 한 페이지에 게시글 몇 개씩 보여줄 것인지
		this.boardLimit = boardLimit;
		
		/*
		maxPage : 가장 마지막 페이지가 몇 번 페이지인지(총 페이지 수)
		
		listCount와 boardLimit에 영향을 받는다. 
		
		게시글이 107개이고, boardLimit이 10이면
		10페이지까지는 가득 차고 나머지 한 페이지에 7개의 게시글이 표현되어야 하기 때문에
		총 11페이지가 나온다 : maxPage = 11
		
		총 개수		boardLimit			 maxPage
		100개	/		10		=10.0		10
		101개	/		10		=10.1		11
		109개	/		10		=10.9		11
		111개	/		10		=11.1		12
		
		총 개수 / boardLimit 나온 결과를 올림 처리 해주면 maxPage 값이 나온다. 
		
		1) listCount를 double 자료형으로 변환(변환 안 하면 int끼리의 계산이라 결과값에서 버림을 해버리기 때문)
		2) listCount / boardLimit
		3) 결과에 대해 올림 처리 Math.ceil() 메소드로 올림
		3) 결과값을 다시 int 자료형으로 변환
		
		*/
		maxPage = (int)(Math.ceil((double)listCount / boardLimit));
		
		
		/*
		 * startPage : 페이징 바의 시작 수
		 * 
		 * pageLimit이 10일 때
		 * 
		 * startPage : 1,11,21,31,41.... == 10의 배수+1 == pageLimit의 배수+1
		 * 
		 * n * pageLimit +1
		 * 
		 * -pageLimit이 10일 때
		 * currentPage		startPage
		 * 		1				1
		 * 		5				1
		 * 		10				1
		 * 
		 * 		11				11
		 * 		15				11
		 * 
		 * 		21				21
		 * 		25				21
		 * 		29				21
		 * 
		 * currentPage : 1~10 - startPage : 1	(10-1)=9 -> 9/10*10+1 = 1
		 * currentPage : 11~20 - startPage : 11	(11-1)=10 -> 10/10*10+1 = 11
		 * currentPage : 21~30 - startPage : 21	(25-1)=24 -> 24/10*10+1 = 21
		 * 
		 * startPage = (currentPage-1)/pageLimit * pageLimit + 1
		 * 
		 * startPage 1	- endPage 10
		 * startPage 11	- endPage 20
		 * startPage 21	- endPage 30
		 * 
		 * endPage = startPage + pageLimit -1
		 */
		startPage = (currentPage-1)/pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit -1;
		
		if(endPage>maxPage) {
			endPage = maxPage;
		}
	}

	public PageInfo(int listCount, int currentPage, int pageLimit, int boardLimit, int maxPage, int startPage,
			int endPage) {
		super();
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	
	
	public int getListCount() {
		return listCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [listCount=" + listCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit
				+ ", boardLimit=" + boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
	
	
	
}
