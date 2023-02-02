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

/**
 * Servlet implementation class BoardUpdateFormCnlr
 */
@WebServlet("/updateForm.nsr")
public class AdminUpdateFormCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminUpdateFormCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		int bno = Integer.parseInt(request.getParameter("bno"));
	
		Board b = new BoardService().selectBoard(bno);//게시글번호, 카테고리이름, 제목, 내용, 아이디, 작성일
		request.setAttribute("b", b);
		
		HttpSession session = request.getSession();
		
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
				request.getRequestDispatcher("/views/board/adminUpdateForm.jsp").forward(request, response);			
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}
