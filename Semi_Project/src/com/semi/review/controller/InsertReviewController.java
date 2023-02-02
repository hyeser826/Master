package com.semi.review.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.member.model.vo.Member;
import com.semi.order.model.service.OrderDetailService;
import com.semi.review.model.service.ReviewService;
import com.semi.review.model.vo.Review;

/**
 * Servlet implementation class InsertReviewController
 */
@WebServlet("/write.re")
public class InsertReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReviewController() {
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
		
		System.out.println("리뷰 인서트 시작할 수 있죠?");
		//view에서 productNo, productBoardContent 가져오기
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		int detailOno = Integer.parseInt(request.getParameter("detailOno"));
		int memNo = ((Member)request.getSession().getAttribute("loginMem")).getMemNo();
		String productBoardContent = request.getParameter("productBoardContent");
		String detailOnoText = request.getParameter("detailOnoText");
		
		Review r = new Review();
		r.setProductNo(productNo);
		r.setMemNo(memNo);
		r.setProductBoardContent(productBoardContent);
		
		int result1 = new ReviewService().insertReview(r);
		int result2 = new OrderDetailService().chStatus(detailOno, detailOnoText);

		int finalResult = result1*result2;
		
		if(finalResult>0) {
		    request.getSession().setAttribute("alertMsg", "리뷰 작성이 완료되었습니다.");
		    response.sendRedirect(request.getContextPath()+"???");
		}else {
		    request.getSession().setAttribute("alertMsg", "리뷰 작성에 실패했습니다.");
		    response.sendRedirect(request.getContextPath()+"???");
		}
	}

}
