package com.semi.manager.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.manager.model.service.ManagerService;
import com.semi.manager.master.model.vo.Master;

/**
 * Servlet implementation class MasterInfoController
 */
@WebServlet("/masterDetail.ma")
public class MasterDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memNo = Integer.parseInt(request.getParameter("mno"));
		ArrayList<Master> list = new ManagerService().selectMasterList(memNo);
		request.setAttribute("list", list);
		HttpSession session = request.getSession();
		try {
			if(!session.getAttribute("loginMem").equals(null)) {
			request.getRequestDispatcher("/views/manager/masterDetailView.jsp").forward(request, response);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
			session.setAttribute("alertMsg","로그인후 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
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
