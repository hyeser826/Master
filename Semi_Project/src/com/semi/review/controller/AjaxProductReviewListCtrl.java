package com.semi.review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.review.model.service.ReviewService;
import com.semi.review.model.vo.Review;

/**
 * Servlet implementation class AjaxProductReviewListCtrl
 */
@WebServlet("/productReviewList.re")
public class AjaxProductReviewListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxProductReviewListCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			int pno = Integer.parseInt(request.getParameter("pno"));
			ArrayList<Review> list = new ReviewService().selectProductReviewList(pno);
			
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(list,response.getWriter());
			
			
			System.out.println(list);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
