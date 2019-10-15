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
//		//����ҵ��㹦�ܣ���ȡȫ��������Ϣ�����ؼ���
//		CategoryService CategoryService=new CategoryServiceImp();
//		List<Category> list=CategoryService.getAllCats();
//		//�����صļ��Ϸ���request
//		req.setAttribute("allCats", list);
//		//ת������ʵ����ҳ
//		return "/jsp/index.jsp";
//	}
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductService ProductService=new ProductServiceImp();
		//����ҵ����ѯ������Ʒ����ѯ������Ʒ������2������
		List<Product> list01=ProductService.findHots();
		List<Product> list02=ProductService.findNews();
		//��2�����Ϸ��뵽request��
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		return "/jsp/index.jsp";
		
		
	}

	

}
