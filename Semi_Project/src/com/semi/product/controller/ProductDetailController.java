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
 * Servlet implementation class ProductDetailController
 */
@WebServlet("/detail.pr")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProductDetailController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//thumbnailListView에서 해당 글을 클릭하면 detail.pr로 요청을 보내 
		int pno = Integer.parseInt(request.getParameter("pNo"));
		
		//기존 조회수 증가 메소드 사용
		int result = new ProductService().increaseCount(pno);
		
		if(result>0) {
			//조회수 증가가 성공적으로 이루어졌으면
			//게시글 정보와 첨부파일 정보를 조회해온다.
			Product p = new ProductService().selectProduct(pno);
			ArrayList<Attachment> list = new ProductService().selectAttachmentList(pno);
			
			
			request.setAttribute("p", p);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/views/board/productDetailView.jsp").forward(request, response);
		}else {
			
			request.setAttribute("errorMsg", "게시글 조회 실패");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
		//조회수 증가 및 글 정보 조회하여 
		//thumbnailDetailView로 이동해서 데이터를 보여주기 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
