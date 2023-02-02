package com.semi.JY.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.JY.productboard.model.vo.ProductBoard;
import com.semi.JY.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewDeleteController
 */
@WebServlet("/review.del")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//게시글 상태만 N으로 바꿔줄려고 UPDATE 써서 ProductBoard테이블에서! 내가쓴리뷰니까 MemNo 가져가기!
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 상태만 N으로 바꿔줄려고 UPDATE 써서 ProductBoard테이블에서! 내가쓴리뷰니까 MemNo 가져가기!
		HttpSession session = request.getSession();
		Member loginMem = (Member)session.getAttribute("loginMem");	
		//회원번호 담아오기.
		int memNo = loginMem.getMemNo();
		
		int result=new ReviewService().ReviewDelete(memNo);
		if(result>0) {
			response.sendRedirect(request.getContextPath()+"/list.rv");
		}else {
			
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
