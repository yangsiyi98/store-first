package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Order;
import com.store.service.OrderService;
import com.store.service.Imp.OrderServiceImp;
import com.store.web.base.BaseServlet;


import net.sf.json.JSONArray;


public class AdminOrderServlet extends BaseServlet {
	

	public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		OrderService OrderService=new OrderServiceImp();
		
		String st=req.getParameter("state");
		List<Order> list=null;
		if(null==st||"".equals(st)){
			//��ȡ��ȫ������
			list=OrderService.findAllOrders();			
		}else{
			list=OrderService.findAllOrders(st);
		}
		//��ȫ����������request
		req.setAttribute("allOrders", list);
		//ת�� /admin/order/list.jsp
		return "/admin/order/list.jsp";
	}
	//findOrderByOidWithAjax
	public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//����˻�ȡ������ID,
		String oid=req.getParameter("id");
		//��ѯ������������еĶ������Լ��������Ӧ����Ʒ��Ϣ,���ؼ���
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		//�����صļ���ת��ΪJSON��ʽ�ַ���,��Ӧ���ͻ���
		String jsonStr=JSONArray.fromObject(order.getList()).toString();
		//��Ӧ���ͻ���
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println(jsonStr);
		return null;
	}
	
	//updateOrderByOid
	public String updateOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//��ȡ����ID
		String oid=req.getParameter("oid");
		//���ݶ���ID��ѯ����
		OrderService OrderService=new OrderServiceImp();
		Order order=OrderService.findOrderByOid(oid);
		//���ö���״̬
		order.setState(3);
		//�޸Ķ�����Ϣ
		OrderService.updateOrder(order);
		//���¶��򵽲�ѯ�ѷ�������
		resp.sendRedirect("/store/AdminOrderServlet?method=findOrders&state=3");
		return null;
	}
}
