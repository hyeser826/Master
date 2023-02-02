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
 * Servlet implementation class AjaxProductBestList
 */
@WebServlet("/best.li")
public class AjaxProductBestList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxProductBestList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoryName= request.getParameter("categoryName");
		ArrayList<Product> bestProductList = new ProductService().selectBestProductList(categoryName);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(bestProductList,response.getWriter());
		
		
		System.out.println(bestProductList);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
