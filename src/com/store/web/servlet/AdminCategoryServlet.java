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
		//��ȡȫ��������Ϣ
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		//����request
		req.setAttribute("allCats", list);
		//ת����/admin/category/list.jsp
		return "/admin/category/list.jsp";
	}
	//addCategoryUI
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		
		return "/admin/category/add.jsp";
	}
	//addCategory
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		//��ȡ������Ϣ
		String cname=req.getParameter("cname");
		//��������ID
		String id=UUIDUtils.getId();
		Category c=new Category();
		c.setCid(id);
		c.setCname(cname);
		//����ҵ�����ӷ��๦��
		CategoryService CategoryService=new CategoryServiceImp();
		CategoryService.addCategory(c);
		//�ض��򵽲�ѯȫ��������Ϣ
		resp.sendRedirect("/store/AdminCategoryServlet?method=findAllCats");
		return null;
	}
}
