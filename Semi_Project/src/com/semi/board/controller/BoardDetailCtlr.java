package com.semi.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.board.model.dao.BoardDao;
import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;


/**
 * Servlet implementation class NoticeDetailCtlr
 */
@WebServlet("/detail.psn")
public class BoardDetailCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		//조회수 증가 / 조회수 증가가 성공적으로 이루어졌다면 게시글 번호로 게시글 조회 / 게시글 번호로 첨부파일 조회
		//조회해온 데이터 담아서 상세보기 페이지로 포워딩
		//실패시 에러페이지

		Board b = new BoardService().selectBoard(bno);
		
		if(b!=null) {
			
			request.setAttribute("b", b);
			HttpSession session = request.getSession();
			
			try {
				if(!session.getAttribute("loginMem").equals(null)) {
					request.getRequestDispatcher("/views/board/personalDetailView.jsp").forward(request, response);
				}
			} catch(NullPointerException e){
				e.printStackTrace();
				session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
				response.sendRedirect(request.getContextPath());
			}
			
		} else {
			request.setAttribute("errorMsg", "해당 게시글을 조회할 수 없습니다.");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}
		
	}
	
}
