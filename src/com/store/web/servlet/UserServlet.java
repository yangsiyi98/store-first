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
		
		//接受表单参数
		Map<String,String[]> map=request.getParameterMap();
		User user=new User();
		
		MyBeanUtils.populate(user, map);
		//为用户的其他属性赋值
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);
		
//			//遍历map中的数据
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
			
		//调用业务层注册功能
		UserService UserService=new UserServiceImp();
		try {
			UserService.userRegist(user);;
			//注册成功，向用户邮箱发送信息，跳转到提示页面
			//发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "用户注册成功，请激活！");
			
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "用户注册失败，请重新注册！");
			
		}
		
		//注册失败， 跳转到提示页面
		return "/jsp/info.jsp";
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取激活码
		String code=request.getParameter("code");
		//调用业务层激活功能
		UserService UserService=new UserServiceImp();
		boolean flag=UserService.userActive(code);
		
		//进行激活信息提示
		if(flag==true){
			//用户激活成功,向request放入提示信息,转发到登录页面
			request.setAttribute("msg", "用户激活成功,请登录!");
			return "/jsp/login.jsp";
		}else{
			//用户激活失败,向request放入提示信息,转发到提示页面
			request.setAttribute("msg", "用户激活失败,请重新激活!");
			return  "/jsp/info.jsp";
		}
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/login.jsp";
	}
	//userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//获取用户数据
		User user=new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		
		//调用业务层登陆功能
		UserService UserService=new UserServiceImp();
		User user02=null;
	
				
		try {
			//用户登陆成功
			user02=UserService.userLogin(user);
			//登陆成功将用户信息放入session
			request.getSession().setAttribute("loginUser",user02);
			response.sendRedirect("/store/index.jsp");
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			//用户登陆失败
			String msg=e.getMessage();
			System.out.println(msg);
			//向request放入失败信息
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";
		}
		
		
	}
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//清楚session
		 request.getSession().invalidate();
		 //重定向到首页
		 response.sendRedirect("/store/index.jsp");
		return null;
	}

}
