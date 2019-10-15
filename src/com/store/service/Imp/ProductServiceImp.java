package com.store.service.Imp;

import java.util.List;

import com.store.dao.ProductDao;
import com.store.dao.Imp.ProductDaoImp;
import com.store.domain.PageModel;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;


public class ProductServiceImp implements ProductService {
	ProductDao ProductDao=(ProductDao)BeanFactory.createObject("ProductDao");
	@Override
	public List<Product> findHots() throws Exception {
		
		return ProductDao.findHots();
	}

	@Override
	public List<Product> findNews() throws Exception {
		
		return ProductDao.findNews();
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		
		return ProductDao.findProductByPid(pid);
	}

	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		// 1、创建pagemodel对象  目的：计算分页参数	
		//统计当前分类下商品个数
		int totalRecords=ProductDao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum, totalRecords, 12);
		// 2、关联集合
		List list=ProductDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		// 3、关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

	@Override
	public PageModel finAllProductsWithPage(int curNum) throws Exception {
		// TODO Auto-generated method stub
		//1、创建对象
		int totalRecords=ProductDao.findTotalRecords();
		PageModel pm=new PageModel(curNum, totalRecords, 5);
		//2、关联集合
		List<Product>list=ProductDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3、关联url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		ProductDao.saveProduct(product);
	}
	
}
