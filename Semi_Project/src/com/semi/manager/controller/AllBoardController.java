package com.semi.manager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.board.model.vo.Board;
import com.semi.common.model.vo.PageInfo;
import com.semi.manager.model.service.ManagerService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class AllQuestionController
 */
@WebServlet("/allBoard.ma")
public class AllBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount; //현재 총 게시물 개수
		int currentPage; //현재 페이지(즉, 사용자가 요청한 페이지)
		int pageLimit; //페이지 하단에 보여질 페이징 바의 페이지 최대 개수
		int boardLimit; //한 페이지에 보여질 게시글의 최대 개수(몇 개 단위씩 보여줄 건지)
		
		int maxPage; //가장 마지막 페이지가 몇 번 페이지인지(총 페이지 수)
		int startPage; //페이지 하단에 보여질 페이징 바의 시작수
		int endPage; //페이지 하단에 보여질 페이징 바의 끝수
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		listCount = new ManagerService().selectBoardListCount(memNo);
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		pageLimit = 5;
		boardLimit = 10;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		Member m = new ManagerService().selectMemberDetail(memNo);
		ArrayList<Board> boardList = new ManagerService().selectBoardList(pi,memNo);
		if(!boardList.isEmpty()) {
			for(Board b : boardList) {
				switch(b.getStatus()) {
				case "W" : b.setStatus("미답변"); break;
				case "Y" : b.setStatus("답변완료"); break;
				case "N" : b.setStatus("삭제"); break;
				}
				switch(b.getBoardCategory()) {
				case "PERSONAL" : b.setBoardCategory("일반문의"); break;
				case "LEVELUP" : b.setBoardCategory("입점문의"); break;
				}
			}
		}
		HttpSession session = request.getSession();
		
		if(m!=null) {//회원 정보 상세보기
			request.setAttribute("pi", pi);
			request.setAttribute("m", m);
			request.setAttribute("boardList", boardList);
			
			
			try {
				if(!session.getAttribute("loginMem").equals(null)) {
					request.getRequestDispatcher("/views/manager/allQuestion.jsp").forward(request, response);
				}
			} catch(NullPointerException e){
				e.printStackTrace();
				session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
				response.sendRedirect(request.getContextPath());
			}
		}else {//회원 정보 상세보기 실패
			session.setAttribute("alertMsg", "회원 정보 상세보기 실패");
			response.sendRedirect(request.getContextPath());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
