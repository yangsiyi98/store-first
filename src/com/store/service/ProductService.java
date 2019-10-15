package com.store.service;

import java.util.List;

import com.store.domain.PageModel;
import com.store.domain.Product;

public interface ProductService {

	List<Product> findHots()throws Exception;

	List<Product> findNews()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	PageModel findProductsByCidWithPage(String cid, int curNum)throws Exception;

	PageModel finAllProductsWithPage(int curNum)throws Exception;

	void saveProduct(Product product)throws Exception;

}
