package com.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.User;
import com.store.service.UserService;
import com.store.service.Imp.UserServiceImp;
import com.store.utils.MailUtils;
import com.store.utils.MyBeanUtils;
import com.store.utils.UUIDUtils;
import com.store.web.base.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/register.jsp";
	}
	
	
	//userRegist
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ܱ�����
		Map<String,String[]> map=request.getParameterMap();
		User user=new User();
		
		MyBeanUtils.populate(user, map);
		//Ϊ�û����������Ը�ֵ
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);
		
//			//����map�е�����
//			Set<String> keySet = map.keySet();
//			Iterator<String> iterator = keySet.iterator();
//			while(iterator.hasNext()){
//				String str = iterator.next();
//				String[] strs = map.get(str);
//				for (String string : strs) {
//					System.out.println(string);
//				}
//				System.out.println();
//			}
			
		//����ҵ���ע�Ṧ��
		UserService UserService=new UserServiceImp();
		try {
			UserService.userRegist(user);;
			//ע��ɹ������û����䷢����Ϣ����ת����ʾҳ��
			//�����ʼ�
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "�û�ע��ɹ����뼤�");
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "�û�ע��ʧ�ܣ�������ע�ᣡ");
			
		}
		
		//ע��ʧ�ܣ� ��ת����ʾҳ��
		return "/jsp/info.jsp";
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//��ȡ������
		String code=request.getParameter("code");
		//����ҵ��㼤���
		UserService UserService=new UserServiceImp();
		boolean flag=UserService.userActive(code);
		
		//���м�����Ϣ��ʾ
		if(flag==true){
			//�û�����ɹ�,��request������ʾ��Ϣ,ת������¼ҳ��
			request.setAttribute("msg", "�û�����ɹ�,���¼!");
			return "/jsp/login.jsp";
		}else{
			//�û�����ʧ��,��request������ʾ��Ϣ,ת������ʾҳ��
			request.setAttribute("msg", "�û�����ʧ��,�����¼���!");
			return  "/jsp/info.jsp";
		}
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/login.jsp";
	}
	//userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//��ȡ�û�����
		User user=new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		
		//����ҵ����½����
		UserService UserService=new UserServiceImp();
		User user02=null;
	
				
		try {
			//�û���½�ɹ�
			user02=UserService.userLogin(user);
			//��½�ɹ����û���Ϣ����session
			request.getSession().setAttribute("loginUser",user02);
			response.sendRedirect("/store/index.jsp");
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			//�û���½ʧ��
			String msg=e.getMessage();
			System.out.println(msg);
			//��request����ʧ����Ϣ
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
		
		
	}
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//���session
		 request.getSession().invalidate();
		 //�ض�����ҳ
		 response.sendRedirect("/store/index.jsp");
		return null;
	}

}
