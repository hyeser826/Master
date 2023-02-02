package com.semi.master.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.master.model.service.MasterService;
import com.semi.master.model.vo.Master;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class MasterIntroUpdateController
 */
@WebServlet("/updatems.in")
public class MasterIntroUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterIntroUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int masterNo = Integer.parseInt(request.getParameter("masterNo"));
		String masterIntro = request.getParameter("text");
		
		Master m = new Master();
		m.setMasterNo(masterNo);
		m.setMasterIntro(masterIntro);
		
		int result = new MasterService().updateMasterIntro(m);
		
		if(result>0) {
			request.getSession().setAttribute("alretMSg","게시글 수정 성공");
			response.sendRedirect(request.getContextPath()+"/mypagepr.ms");
		}else {
			request.setAttribute("errorMSg","게시글 수정 실패");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request,response);
		}
	}

}
