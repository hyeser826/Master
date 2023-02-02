package com.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.common.model.vo.PageInfo;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class BoardListCtlr
 */
@WebServlet("/list.qna")
public class QnaListCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaListCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productNumber = 1;
//		페이징 처리
		int listCount; //현재 총 게시물 개수
		int currentPage; //현재페이지(사용자가 요청한 페이지)
		int pageLimit; //페이지 하단에 보여질 베이징바의 최대 개수
		int boardLimit; //한 페이지에 보여질 게시글의 최대개수(몇개 단위씩 보여질건지)
		
		int maxPage; //가장 마지막 페이지가 몇번페이지 인지(총 페이지수)
		int startPage; //페이지 하단에 보여질 페이징바의 시작수
		int endPage; //페이지 하단에 보여질 페이징바의 끝수
		
		Member loginMem = (Member)request.getSession().getAttribute("loginMem");
		String memId = loginMem.getMemId();
		System.out.println(memId);
		
		//listCount : 현재 총 게시물 개수
		listCount = new BoardService().selectQnaCount(memId);
		
		//currentPage : 현재페이지(사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//pageLimit : 페이지 하단에 보여질 베이징바의 최대 개수
		pageLimit = 10;
		
		//boardLimit : 한 페이지에 보여질 게시글의 최대개수(몇개 단위씩 보여질건지)
		boardLimit = 10;
		

		maxPage = (int)(Math.ceil((double)listCount / boardLimit));
		
		startPage = (currentPage-1)/pageLimit * pageLimit +1;

		endPage = startPage + pageLimit -1;		
		
		
		if(endPage>maxPage) {
			endPage = maxPage;
		}
	
		PageInfo pi2 = new PageInfo(currentPage);
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, 
									boardLimit, maxPage, startPage, endPage);
		
		
			ArrayList<Board> list = new BoardService().selectQnaList(pi,memId);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);		
			request.setAttribute("productNumber", productNumber);
			
			
			request.getRequestDispatcher("/views/board/qnaListView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
