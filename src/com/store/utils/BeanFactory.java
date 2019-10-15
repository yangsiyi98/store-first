package com.store.utils;

	import java.io.InputStream;
	import java.sql.SQLException;
	import java.util.List;

	import org.dom4j.Document;
	import org.dom4j.Element;
	import org.dom4j.io.SAXReader;

import com.store.dao.UserDao;
import com.store.domain.User;

	public class BeanFactory {

		
		//����XML
		public static Object createObject(String name) {
			try {
				//ͨ�����ݹ�����name��ȡapplication.xml��name��Ӧ��classֵ
				
				//��ȡ��Document����
				SAXReader reader=new SAXReader();
				//�����ȡapplication.xml�ļ��������� (application.xml����λ��src��)
				InputStream is=BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
				Document doc=reader.read(is);
				//ͨ��Document�����ȡ���ڵ�  beans
				Element rootElement = doc.getRootElement();
				//ͨ�����ڵ��ȡ�����ڵ������е��ӽڵ� bean,���ؼ���
				List<Element> list = rootElement.elements();
				//��������,�ж�ÿ��Ԫ���ϵ�id��ֵ�Ƿ�͵�ǰ��nameһ��
				for (Element ele : list) {
					//ele�൱��beans�ڵ��µ�ÿ��bean
					//��ȡ����ǰ�ڵ��id����ֵ
					//���һ��,��ȡ����ǰԪ����class����ֵ
					String id=ele.attributeValue("id");
					if(id.equals(name)){
						String str=ele.attributeValue("class");
						//ͨ�����䴴�������ҷ���
						Class clazz=Class.forName(str);
						//����classֵͨ�����䴴�����󷵻�
						return  clazz.newInstance();
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public static void main(String[] args) throws SQLException {
			UserDao ud=(UserDao)BeanFactory.createObject("UserDao");
			User user=new User();
			user.setUsername("aaa");
			user.setPassword("aaa");
			User uu = ud.userLogin(user);
			System.out.println(uu);
		}
		
	}

