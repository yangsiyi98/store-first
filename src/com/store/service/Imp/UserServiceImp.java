package com.store.service.Imp;

import java.sql.SQLException;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.utils.BeanFactory;



public class UserServiceImp implements com.store.service.UserService{
	UserDao UserDao=(UserDao)BeanFactory.createObject("UserDao");
	@Override
	public void userRegist(User user) throws SQLException {
		// TODO Auto-generated method stub
		//实现注册功能
		
		UserDao.userRegist(user);
		
	}

	
	@Override
	public boolean userActive(String code) throws SQLException {
		// 实现注册功能
		User user=UserDao.userActive(code);
		if(null!=user){
			//可以根据激活码查询到一个用户
			//修改用户的状态，清除激活码
			user.setState(1);
			user.setCode(null);
			//对数据库进行一次真实操作
			UserDao.updateUser(user);
			
			return true;
		}else{
			//查询不到用户
			return false;
		}
	
	}

	@Override
	public User userLogin(User user) throws SQLException {
		//可以利用异常在模块之间传递数据
		User uu=UserDao.userLogin(user);
		if(uu==null){
			throw new RuntimeException("密码不正确");
		}else if(uu.getState()==0){
			throw new RuntimeException("用户未激活");
		}else{
			return uu;
		}
		
	}

}
