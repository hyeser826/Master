package com.semi.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.notice.model.service.NoticeService;
import com.semi.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertCtlr
 */
@WebServlet("/insert.fq")
public class FaqInsertCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqInsertCtlr() {
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
		String memId = (String)request.getParameter("memId");
		String title = (String)request.getParameter("title");
		String content = (String)request.getParameter("content");
		String category = (String)request.getParameter("category");

		
		int result = new NoticeService().insertNotice(memId, title, content, category);
		
		if(result>0) {
		request.getSession().setAttribute("alertMsg", "공지사항이 등록되었습니다.");
		response.sendRedirect(request.getContextPath()+"/list.fq");
		
		} else {
		request.setAttribute("errorMsg", "공지사항이 등록에 실패했습니다.");
		request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);

		}
		
		
	}

}
