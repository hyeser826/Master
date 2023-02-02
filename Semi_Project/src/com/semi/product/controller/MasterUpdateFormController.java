package com.semi.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.product.model.service.ProductService;
import com.semi.product.model.vo.Attachment;
import com.semi.product.model.vo.Product;

/**
 * Servlet implementation class MasterUpdateFormController
 */
@WebServlet("/updateForm.pr")
public class MasterUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService pService = new ProductService();
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		//System.out.println("업데이트폼컨트롤러 : "+pno);
		//수정페이지로 가기 전에 가지고 와야할 데이터
		//카테고리 목록, 해당 게시글 정보, 첨부파일 정보
		
		Product p = pService.selectProduct(pno);
			
		ArrayList<Attachment> list = pService.selectAttachmentList(pno);//null || 파일번호,원본이름,변경이름,경로
		
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/product/productUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
