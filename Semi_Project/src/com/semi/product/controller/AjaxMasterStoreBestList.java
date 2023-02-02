package com.semi.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.semi.product.model.service.ProductService;
import com.semi.product.model.vo.Product;

/**
 * Servlet implementation class AjaxMasterStoreBestList
 */
@WebServlet("/mBest.li")
public class AjaxMasterStoreBestList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMasterStoreBestList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int mno = Integer.parseInt(request.getParameter("mno"));
		ArrayList<Product> mbestList = new ProductService().selectMasterStoreBestList(mno);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(mbestList,response.getWriter());
		
		
		System.out.println(mbestList);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
