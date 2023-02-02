package com.semi.JY.masterinquired.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.member.model.vo.Member;
import com.semi.JY.product.model.service.ProductService;
import com.semi.JY.product.model.vo.Product;
import com.semi.JY.productboard.model.service.ProductBoardService;
import com.semi.JY.productboard.model.vo.ProductBoard;

/**
 * Servlet implementation class MasterInquired
 */
//장인 1:1 문의 
@WebServlet("/master.inq")
public class MasterInquiredController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterInquiredController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String Calendar = request.getParameter("Calendar");
		//유저 가져오기  여기선 굳이 필요없을거같긴한데 일단가져왔음 .
		Member loginMem = (Member)session.getAttribute("loginMem");	
		int memNo = loginMem.getMemNo();
	
			//아래는 p select 
		Product p = new Product();
		p=new ProductService().selectproductList();
		
		System.out.println("p :"+p);
		
		//productBoard 리스트 가져올예정 
		ProductBoard pb =new ProductBoardService().selectProductBoard(memNo);
		
		ArrayList<ProductBoard>list =new ProductBoardService().masterinquired(p,pb,memNo);
		System.out.println("1:1문의 list"+list);
		
		request.setAttribute("list",list);
	
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(Calendar);
		
		
		if (session.getAttribute("loginMem") == null) {
			session.setAttribute("alert", "로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			request.getRequestDispatcher("views/jy/masterinquired/masterInquired.jsp").forward(request, response);
		}
	}

}
