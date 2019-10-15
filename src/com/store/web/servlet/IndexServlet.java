package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.Imp.CategoryServiceImp;
import com.store.service.Imp.ProductServiceImp;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {
	
	
//	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		//调用业务层功能：获取全部分类信息，返回集合
//		CategoryService CategoryService=new CategoryServiceImp();
//		List<Category> list=CategoryService.getAllCats();
//		//将返回的集合放入request
//		req.setAttribute("allCats", list);
//		//转发到真实的首页
//		return "/jsp/index.jsp";
//	}
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService ProductService=new ProductServiceImp();
		//调用业务层查询最新商品，查询最热商品，返回2个集合
		List<Product> list01=ProductService.findHots();
		List<Product> list02=ProductService.findNews();
		//将2个集合放入到request中
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		return "/jsp/index.jsp";
		
		
	}

	

}
