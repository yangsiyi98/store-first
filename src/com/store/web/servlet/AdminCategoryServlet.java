package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.Imp.CategoryServiceImp;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	//findAllCats
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		//获取全部分类信息
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		//放入request
		req.setAttribute("allCats", list);
		//转发到/admin/category/list.jsp
		return "/admin/category/list.jsp";
	}
	//addCategoryUI
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		
		return "/admin/category/add.jsp";
	}
	//addCategory
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		//获取分类信息
		String cname=req.getParameter("cname");
		//创建分类ID
		String id=UUIDUtils.getId();
		Category c=new Category();
		c.setCid(id);
		c.setCname(cname);
		//调用业务层添加分类功能
		CategoryService CategoryService=new CategoryServiceImp();
		CategoryService.addCategory(c);
		//重定向到查询全部分类信息
		resp.sendRedirect("/store/AdminCategoryServlet?method=findAllCats");
		return null;
	}
}
