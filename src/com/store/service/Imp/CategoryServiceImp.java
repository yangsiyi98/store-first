package com.store.service.Imp;

import java.util.List;

import com.store.dao.CategoryDao;
import com.store.dao.Imp.CategoryDaoImp;
import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.utils.BeanFactory;



public class CategoryServiceImp implements CategoryService {
	CategoryDao CategoryDao=(CategoryDao)BeanFactory.createObject("CategoryDao");
	@Override
	public List<Category> getAllCats() throws Exception {
		// TODO Auto-generated method stub
		
		return CategoryDao.getAllCats();
	}

	@Override
	public void addCategory(Category c) throws Exception {
		// TODO Auto-generated method stub
		CategoryDao.addCategory(c);
	}



}
