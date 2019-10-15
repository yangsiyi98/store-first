package com.store.dao.Imp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public List<Category> getAllCats() throws Exception {
		// TODO Auto-generated method stub
		String sql="select * from category";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}

	@Override
	public void addCategory(Category c) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into category values(? ,?)";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCid(),c.getCname());
	}

}
