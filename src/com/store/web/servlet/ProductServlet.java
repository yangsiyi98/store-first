package com.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.Imp.ProductServiceImp;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取商品pid
		String pid=request.getParameter("pid");
		//根据商品pid查询商品信息
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);
		//将获取到的商品放入request
		request.setAttribute("product", product);
		//转发到product_info
		return "jsp/product_info.jsp";
		
	}
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取cid，num
		String cid=request.getParameter("cid");
		int curNum=Integer.parseInt(request.getParameter("num"));
		//调用业务层功能：以分页形式查询当前类别下商品信息
		//返回pageModel对象（1、当页商品信息  2、分页  3、url）
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(cid,curNum);
		
		//将pageModel对象放入request
		request.setAttribute("page", pm);
		//转发到product_info
		return "jsp/product_list.jsp";
		
	}

}
