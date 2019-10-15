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
		//��ȡ��Ʒpid
		String pid=request.getParameter("pid");
		//������Ʒpid��ѯ��Ʒ��Ϣ
		ProductService ProductService=new ProductServiceImp();
		Product product=ProductService.findProductByPid(pid);
		//����ȡ������Ʒ����request
		request.setAttribute("product", product);
		//ת����product_info
		return "jsp/product_info.jsp";
		
	}
	//findProductsByCidWithPage
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��ȡcid��num
		String cid=request.getParameter("cid");
		int curNum=Integer.parseInt(request.getParameter("num"));
		//����ҵ��㹦�ܣ��Է�ҳ��ʽ��ѯ��ǰ�������Ʒ��Ϣ
		//����pageModel����1����ҳ��Ʒ��Ϣ  2����ҳ  3��url��
		ProductService ProductService=new ProductServiceImp();
		PageModel pm=ProductService.findProductsByCidWithPage(cid,curNum);
		
		//��pageModel�������request
		request.setAttribute("page", pm);
		//ת����product_info
		return "jsp/product_list.jsp";
		
	}

}
