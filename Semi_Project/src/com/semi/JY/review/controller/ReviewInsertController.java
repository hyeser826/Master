package com.semi.JY.review.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import com.semi.member.model.vo.*;
import com.semi.JY.pom.model.service.DeliveryService;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.productboard.model.service.ProductBoardService;
import com.semi.JY.productboard.model.vo.ProductBoard;
import com.semi.JY.review.model.service.ReviewService;

/**
 * Servlet implementation class ReviewinsertController
 */

//리뷰 수정    내용만 수정가능함 .

@WebServlet("/insert.re")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
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
			
			//아래는 한글
			request.setCharacterEncoding("UTF-8");
		
			//세션 선언
			HttpSession session = request.getSession();
			
			Member loginMem = (Member)session.getAttribute("loginMem");	
			//회원번호 담아오기.
			int memNo = loginMem.getMemNo();
			
			//ProductBoard 리스트조회
			ProductBoard pb =new ProductBoardService().selectProductBoard(memNo);
			
			pb.setProductBoardContent(request.getParameter("pqContent")); //리뷰내용
			
			
			//ProductBoard 에서   리뷰내용만  넣을예정  
			int result=new ReviewService().reviewModifyContent(pb);
			
			if(result>0) {
				response.sendRedirect(request.getContextPath()+"/list.rv");
			}else {
				
			}

	}


	
}
