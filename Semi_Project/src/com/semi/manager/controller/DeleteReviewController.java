package com.semi.manager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.manager.model.service.ManagerService;

/**
 * Servlet implementation class DeleteReviewController
 */
@WebServlet("/deleteReview.ma")
public class DeleteReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int productBoardNo = Integer.parseInt(request.getParameter("pbNo"));
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		int result = new ManagerService().deleteReview(productBoardNo);
		
		if(result>0) {//성공
			request.getSession().setAttribute("alertMsg", "리뷰가 삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.ma?mno="+mno);
		}else {//실패
			request.getSession().setAttribute("alertMsg", "리뷰 삭제에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/detail.ma?mno="+mno);
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
