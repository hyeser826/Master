package com.semi.manager.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.manager.model.service.ManagerService;
import com.semi.member.model.vo.Member;

/**
 * Servlet implementation class ManagerSearchViewController
 */
@WebServlet("/search.ma")
public class ManagerSearchViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerSearchViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memName = request.getParameter("memName");
		String memPhone = request.getParameter("memPhone");
		
		Member m = new ManagerService().selectOneMember(memName,memPhone);
		
		request.setAttribute("m", m);
		
		HttpSession session = request.getSession();
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
			request.getRequestDispatcher("/views/manager/managerPageView.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}
