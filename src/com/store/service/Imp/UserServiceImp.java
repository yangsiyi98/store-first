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
		//ʵ��ע�Ṧ��
		
		UserDao.userRegist(user);
		
	}

	
	@Override
	public boolean userActive(String code) throws SQLException {
		// ʵ��ע�Ṧ��
		User user=UserDao.userActive(code);
		if(null!=user){
			//���Ը��ݼ������ѯ��һ���û�
			//�޸��û���״̬�����������
			user.setState(1);
			user.setCode(null);
			//�����ݿ����һ����ʵ����
			UserDao.updateUser(user);
			
			return true;
		}else{
			//��ѯ�����û�
			return false;
		}
	
	}

	@Override
	public User userLogin(User user) throws SQLException {
		//���������쳣��ģ��֮�䴫������
		User uu=UserDao.userLogin(user);
		if(uu==null){
			throw new RuntimeException("���벻��ȷ");
		}else if(uu.getState()==0){
			throw new RuntimeException("�û�δ����");
		}else{
			return uu;
		}
		
	}

}
