package com.semi.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.board.model.service.BoardService;
import com.semi.board.model.vo.Board;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class AjaxProductInsertQnA
 */
@WebServlet("/productInsert.qna")
public class AjaxProductInsertQnA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxProductInsertQnA() {
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
		
		Member loginMem = (Member)request.getSession().getAttribute("loginMem");     
	//	String categoryName = request.getParameter("categoryName");
		int pno = Integer.parseInt(request.getParameter("pno"));
		String strName = request.getParameter("strName");
		String ptitle = request.getParameter("ptitle");
		String pcontent = request.getParameter("pcontent");
		
//		String categoryName = "4.악세사리";
//		String memId = "user1";
//		int pno = 1;
//		String strName = "김순덕장인홈";
//		
//		String title = "대량구매 관련";
//		String content = "저희가족 대식구인데 맞춰서 사용해보려고요! 가능한가요?";
			
		/*
		 * String pnoLink;
		 * 
		 * if(categoryName.equals("0.체험")) { pnoLink =
		 * "http://localhost:8888/smp/detail.ac?pno="+pno; }else { pnoLink =
		 * "http://localhost:8888/smp/detail.md?pno="+pno; }
		 * 
		 * String totalContent = pnoLink+" 에대한 내용입니다. ["+content+"]";
		 */

		
		System.out.println(ptitle);
		System.out.println(pno);
		System.out.println(pcontent);
		int result=0;
		Board b = new Board();
		
		if(!(loginMem==null)) {
	    String memId = loginMem.getMemId();
		b.setMemId(memId);
		b.setProductNo(pno);
		b.setStrName(strName);
		b.setBoardTitle(ptitle);
		b.setBoardContent(pcontent);
		
		result = new BoardService().insertQnaBoard(b);
		}else {
			result=5;
			
		}
		
		
		System.out.println(b);
	System.out.println(result);

		response.getWriter().print(result);		
			
			
			
		
	}

}
