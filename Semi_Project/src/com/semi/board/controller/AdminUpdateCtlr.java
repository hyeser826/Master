package com.semi.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.service.BoardService;

/**
 * Servlet implementation class BoardUpdateCtlr
 */
@WebServlet("/update.nsr")
public class AdminUpdateCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
		
			int bno = Integer.parseInt(request.getParameter("bno"));		
			String dearPsn = request.getParameter("dearPsn");

			
			int result = new BoardService().updateAnswer(bno, dearPsn);

			if(result>0) {
				request.getSession().setAttribute("alertMsg", "게시글 수정성공");
				response.sendRedirect(request.getContextPath()+"/detail.psn?bno="+bno);
			} else {
				request.setAttribute("errorMsg", "게시글 수정 실패");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}

	}

}
