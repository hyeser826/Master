package com.semi.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeUpdateCtlr
 */
@WebServlet("/update.fq")
public class FaqUpdateCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqUpdateCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		String title = (String)request.getParameter("title");
		String content = (String)request.getParameter("content");
		
		
		int result = new NoticeService().updateNotice(nno,title,content);
		
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "공지사항이 수정되었습니다.");
			response.sendRedirect(request.getContextPath()+"/list.fq");	
			
		} else {
			
			request.setAttribute("errorMsg", "공지사항 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		}
	}

}
