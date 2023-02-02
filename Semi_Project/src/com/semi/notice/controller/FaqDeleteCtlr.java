package com.semi.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.notice.model.service.NoticeService;
import com.semi.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteCtlr
 */
@WebServlet("/deleteForm.fq")
public class FaqDeleteCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqDeleteCtlr() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		삭제하기
		int nno = Integer.parseInt(request.getParameter("nno"));
		System.out.println(nno);
		int result = new NoticeService().deleteNotice(nno);
		
		System.out.println(request.getSession().toString());
		
//		공지 삭제 성공시 alertMsg로 공지사항 삭제 성공 메세지 띄우면서 공지사항 리스트 보여주기
		if(result>0) {
				
			request.getSession().setAttribute("alertMsg", "공지사항을 삭제했습니다.");
			response.sendRedirect(request.getContextPath()+"/list.fq");
		
			
//		실패시 에러페이지에 공지사항 삭제 실패 메세지 띄우기
		} else {
			request.setAttribute("errorMsg", "공지사항을 삭제하지 못했습니다.");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			
		}		
		
	}

}
