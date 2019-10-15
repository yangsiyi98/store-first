package com.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.store.domain.User;

/**
 * Servlet Filter implementation class PriviledgeFilter
 */
public class PriviledgeFilter implements Filter {

  
    public PriviledgeFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myReq=(HttpServletRequest)request;
		//�жϵ�ǰ��session���Ƿ�����ѵ�½�ɹ����û�
		User user=(User)myReq.getSession().getAttribute("loginUser");
		
		if(null!=user){
			//������ڣ�����
			chain.doFilter(request, response);
		}else{
			//��������ڣ�ת����ʾ����
			myReq.setAttribute("msg", "���û��ȵ�½��");
			//ת����ʾҳ��
			myReq.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
		
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
