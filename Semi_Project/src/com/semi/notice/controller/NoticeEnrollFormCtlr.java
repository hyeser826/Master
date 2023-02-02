package com.semi.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NoticeEnrollForm
 */
@WebServlet("/enrollForm.no")
public class NoticeEnrollFormCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeEnrollFormCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		url에 디렉토리 구조 노출시키지 않기 위해 매칭값을 가진 채로 포워딩 작업
		HttpSession session = request.getSession();
		
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
				request.getRequestDispatcher("/views/notice/noticeEnrollForm.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
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
