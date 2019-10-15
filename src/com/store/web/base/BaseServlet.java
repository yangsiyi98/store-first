package com.store.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// localhost:8080/store/productServlet?method=addProduct
		String method = req.getParameter("method");

		if (null == method || "".equals(method) || method.trim().equals("")) {
			method = "execute";
		}

		// ע��:�˴���this�����������Ķ���
		// System.out.println(this);
		// ��������ֽ������
		Class clazz = this.getClass();

		try {
			// ������������Ӧ���ֽ����е�����Ϊmethod�ķ���.��������Ĳ���������:HttpServletRequest.class,HttpServletResponse.class
			Method md = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			if(null!=md){
				String jspPath = (String) md.invoke(this, req, resp);
				if (null != jspPath) {
					req.getRequestDispatcher(jspPath).forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Ĭ�Ϸ���
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		return null;
	}

}