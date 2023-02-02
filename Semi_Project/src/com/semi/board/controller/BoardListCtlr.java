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
@WebServlet("/list.psn")
public class BoardListCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		페이징 처리
		int listCount; //현재 총 게시물 개수
		int currentPage; //현재페이지(사용자가 요청한 페이지)
		int pageLimit; //페이지 하단에 보여질 베이징바의 최대 개수
		int boardLimit; //한 페이지에 보여질 게시글의 최대개수(몇개 단위씩 보여질건지)
		
		int maxPage; //가장 마지막 페이지가 몇번페이지 인지(총 페이지수)
		int startPage; //페이지 하단에 보여질 페이징바의 시작수
		int endPage; //페이지 하단에 보여질 페이징바의 끝수
		
		//listCount : 현재 총 게시물 개수
		listCount = new BoardService().selectListCount();
		
		//currentPage : 현재페이지(사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//pageLimit : 페이지 하단에 보여질 베이징바의 최대 개수
		pageLimit = 10;
		
		//boardLimit : 한 페이지에 보여질 게시글의 최대개수(몇개 단위씩 보여질건지)
		boardLimit = 10;
		
//		maxPage : 가장 마지막 페이지가 몇번페이지 인지(총 페이지수)
//		listCount
//		게시글이 107개라면 boardLimit이 10이면
//		10페이지 까지는 가득차고 나머지 한 페이지에 7개의 게시글이 표현되어야 하기 때문에
//		총 11페이지가 나온다 : maxPage
//		107/10 = 10...7
//		총개수/boardLimit 나온결과를 올림처리 해주면 maxPage
//		1)listCount를 double자료형으로 변환
//		2)listCount / boardLimit
//		3)결과값에 대해 올림처리 Math.ceil() 메소드로 올림
//		4)결과값을 다시 int자료형으로 변환
		maxPage = (int)(Math.ceil((double)listCount / boardLimit));
		
//		startPage : 페이징바의 시작수
//		
//		pageLimit이 10일때
//		
//		startPage : 1, 11, 21, 31, 41,,,, : pageLimit의 배수 +1
//		
//		n * pageLimit + 1
//		
//		currentPage : 1~10 = startPage : 1     9 /10 0 * 10+1 = 1
//		currentPage : 11~20 = startPage : 11  10 /10 1 * 10+1 = 1
//		currentPage : 21~30 = startPage : 21  21 /10 2 * 10+1 = 1
//		
//		startPage = (currentPage-1)/pageLimit * pageLimit +1
//		
//		startPage 1 - endPage 10
//		startPage 11 - endPage 20
//		startPage 21 - endPage 30
//		endPage = startPage + pageLimit -1
		
//		startPage : 페이징바의 시작수
		startPage = (currentPage-1)/pageLimit * pageLimit +1;
//		endPage : 페이징바의 끝수
		endPage = startPage + pageLimit -1;		
		
//		startPage가 11일때 endPage는 20으로 된다.(만약 maxPage가 13이라면)
//		endPage를 maxPage로 변경
		
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
//		페이징 처리구문 VO 생성자에 넣고 생성시 작업되도록
		PageInfo pi2 = new PageInfo(currentPage);
//		System.out.println(pi2+" = 생성자로 만든 pi");
		
		
//		페이지 정보들을 하나의 공간에 담아서 보내자
//		페이지 정보들을 담아줄 VO새성
//		- 공지사항, 사진게시판에서도 사용가능 common 패키지에 생성
		
//		1. 페이징바를 만들기 위해 필요한 객체
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, 
				boardLimit, maxPage, startPage, endPage);
	
		
//		2. 현재 사용자가 요청한 페이지에 대해 보여질 게시글 리스트 조회해오기
		

		
		if(request.getSession().getId()!=null) {
		
			ArrayList<Board> list = new BoardService().selectList(pi);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);		
			
			
			HttpSession session = request.getSession();
			
			Member loginMem = (Member)session.getAttribute("loginMem");
			
			try {
				if(!session.getAttribute("loginMem").equals(null)) {
					request.getRequestDispatcher("/views/board/personalListView.jsp").forward(request, response);
				}
			} catch(NullPointerException e){
				e.printStackTrace();
				session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
				response.sendRedirect(request.getContextPath());
			}
		} else {
			request.setAttribute("alertMsg", "로그인 후 이용가능한 서비스입니다.");
			request.getRequestDispatcher("/views/member/memberLoginView.jsp").forward(request, response);
		}
			
	}


}
