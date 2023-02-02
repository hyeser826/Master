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
 * Servlet implementation class MasterProductDetailController
 */
@WebServlet("/detailms.pr")
public class MasterProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterProductDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt(request.getParameter("pno"));
		System.out.println("상품디테일 pno : "+pno);
		//조회수 증가 / 조회수 증가가 성공적으로 이루어졌다면 게시글번호로 게시글 조회 / 게시글 번호로 첨부파일 조회
//		int result = new ProductService().increaseCount(pno);
		
		int result =1;
		if(result>0) {
			//게시글 정보 가지고 오기
			Product p = new ProductService().selectProduct(pno);
			System.out.println("상품디테일 p : "+p);
			//첨부파일 정보 여러개니까 list로 가지고 오기
			ArrayList<Attachment> list = new ProductService().selectAttachmentList(pno);
			System.out.println("상품디테일 list.get(0) : "+list.get(0));
			
			request.setAttribute("p", p);
			request.setAttribute("list", list);
			
			//조회해온 데이터 담아서 상세보기 페이지로 포워딩
			request.getRequestDispatcher("/views/master/masterProductDetailForm.jsp").forward(request, response);
			
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
