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
 * Servlet implementation class AjaxMasterStoreHighPriceList
 */
@WebServlet("/mHighPrice.li")
public class AjaxMasterStoreHighPriceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxMasterStoreHighPriceList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mno = Integer.parseInt(request.getParameter("mno"));
		ArrayList<Product> mHighPriceList = new ProductService().selectMasterStoreHighPriceList(mno);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(mHighPriceList,response.getWriter());
		
		
		System.out.println(mHighPriceList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
