package com.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.Imp.CategoryServiceImp;
import com.store.utils.JedisUtils;
import com.store.web.base.BaseServlet;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {
	//findAllCats
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		�޷��鿴����spool,��˽��Ż���ҳ���Ҳ���ɾ��
		//Jedis jedis=JedisUtils.getJedis();
//		String jsonStr=jedis.get("allCats");
//		if(null==jsonStr||"".equals(jsonStr)){
		//����ҵ����ȡȫ������
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		//��ȫ������ת��ΪJSON��ʽ������
		String jsonStr=JSONArray.fromObject(list).toString();
		//��ȫ��������Ϣ��Ӧ���ͻ���
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonStr);
//		System.out.println("����û������");
//		}else{
//			System.out.println("����������");
//			//��ȫ��������Ϣ��Ӧ���ͻ���
//			resp.setContentType("application/json;charset=utf-8");
//			resp.getWriter().print(jsonStr);
//		}
//		JedisUtils.closeJedis(jedis);
		return null;
	}

}
