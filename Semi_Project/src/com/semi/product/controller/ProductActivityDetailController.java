package com.semi.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.product.model.service.ProductService;
import com.semi.product.model.vo.Attachment;
import com.semi.product.model.vo.Product;

/**
 * Servlet implementation class ProductActivityDetail
 */
@WebServlet("/detail.ac")
public class ProductActivityDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductActivityDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// thumbnailListView에서 해당글을 클릭하면 detail.th로 요청을 보내
				// 조회수 증가 및 정보 조회하여
				// 썸네일디테일뷰로 이동하여 데이터를 보여주기

				int pno = Integer.parseInt(request.getParameter("pno"));

				// 조회수 증가 / 조회수증가가 성공적으로 이루어졌다면 게시글번호로 게시글 조회 / 게시글 번호로 첨부파일 조회
				int result = new ProductService().increaseCount(pno);
				
				if(result>0) {
					//게시글 정보 가지고 오기
					Product p = new ProductService().selectProduct(pno);
					//첨부파일 정보 가지고 오기 
					ArrayList<Attachment> list = new ProductService().selectAttachmentList(pno);
					
					System.out.println(p);
					System.out.println(list);
					
					request.setAttribute("p", p);
					request.setAttribute("list", list);
					
					request.getRequestDispatcher("/views/product/0_product-activityDetailView.jsp").forward(request, response);
					
				}else {
					//실패시 에러페이지 
					request.setAttribute("errorMsg", "게시글 조회 실패");
					request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
					
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
