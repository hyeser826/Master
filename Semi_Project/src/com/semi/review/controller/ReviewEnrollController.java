package com.semi.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.master.model.service.MasterService;
import com.semi.master.model.vo.Master;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class ReviewEnrollController
 */
@WebServlet("/review.or")
public class ReviewEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("여기는 리뷰단 아아");
		//뷰단에서 클릭한 상세번호의 pno가져오기
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		int detailOno = Integer.parseInt(request.getParameter("detailOno"));
		String detailOnoText = request.getParameter("detailOnoText");
		//System.out.println("productNo"+productNo);
		request.setAttribute("productNo", productNo);
		request.setAttribute("detailOno", detailOno);
		request.setAttribute("detailOnoText", detailOnoText);
		
		//장인 소개 등록/수정 form으로 포워딩
		request.getRequestDispatcher("/views/review/reviewForm.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
