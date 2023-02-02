package com.semi.board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateCtlr
 */
@WebServlet("/update.ctr")
public class MasterUpdateCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterUpdateCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
		
			int bno = Integer.parseInt(request.getParameter("bno"));
			String dearPsn = request.getParameter("dearPsn");

			
			int result = new BoardService().updateAnswer(bno, dearPsn);
			
			
			if(result>0) {
				request.getSession().setAttribute("alertMsg", "QNA 수정성공");
				response.sendRedirect(request.getContextPath()+"/detail.qna?bno="+bno);
			} else {
				request.setAttribute("errorMsg", "QNA 수정 실패");
				request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			}

	}

}
