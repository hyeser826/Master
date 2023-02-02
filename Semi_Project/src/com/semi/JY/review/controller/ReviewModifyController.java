package com.semi.JY.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.semi.JY.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewModify
 */
@WebServlet("/list.rvmo")  //마이페이지에서 내작리 누르고  리뷰수정 누르면 나오는페이지 
public class ReviewModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		
		if(session.getAttribute("loginMem")==null) {
		session.setAttribute("alert","로그인후 가능한 서비스입니다.");
		response.sendRedirect(request.getContextPath());
		}else {
			request.getRequestDispatcher("views/review/reviewEnrollForm.jsp").forward(request, response);
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
