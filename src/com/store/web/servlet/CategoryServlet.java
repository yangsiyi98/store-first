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
//		无法查看本地spool,因此将优化分页查找部分删除
		//Jedis jedis=JedisUtils.getJedis();
//		String jsonStr=jedis.get("allCats");
//		if(null==jsonStr||"".equals(jsonStr)){
		//调用业务层获取全部分类
		CategoryService CategoryService=new CategoryServiceImp();
		List<Category> list=CategoryService.getAllCats();
		//将全部分类转换为JSON格式的数据
		String jsonStr=JSONArray.fromObject(list).toString();
		//将全部分类信息响应到客户端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonStr);
//		System.out.println("缓存没有数据");
//		}else{
//			System.out.println("缓存有数据");
//			//将全部分类信息响应到客户端
//			resp.setContentType("application/json;charset=utf-8");
//			resp.getWriter().print(jsonStr);
//		}
//		JedisUtils.closeJedis(jedis);
		return null;
	}

}
