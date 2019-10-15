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
		// 1������pagemodel����  Ŀ�ģ������ҳ����	
		//ͳ�Ƶ�ǰ��������Ʒ����
		int totalRecords=ProductDao.findTotalRecords(cid);
		PageModel pm=new PageModel(curNum, totalRecords, 12);
		// 2����������
		List list=ProductDao.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		// 3������url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
	}

	@Override
	public PageModel finAllProductsWithPage(int curNum) throws Exception {
		// TODO Auto-generated method stub
		//1����������
		int totalRecords=ProductDao.findTotalRecords();
		PageModel pm=new PageModel(curNum, totalRecords, 5);
		//2����������
		List<Product>list=ProductDao.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3������url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}

	@Override
	public void saveProduct(Product product) throws Exception {
		ProductDao.saveProduct(product);
	}
	
}
