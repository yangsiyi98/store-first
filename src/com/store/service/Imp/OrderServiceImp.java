package com.store.service.Imp;

import java.sql.Connection;
import java.util.List;

import com.store.dao.OrderDao;
import com.store.dao.Imp.OrderDaoImp;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.PageModel;
import com.store.domain.User;
import com.store.service.OrderService;
import com.store.utils.BeanFactory;
import com.store.utils.JDBCUtils;



public class OrderServiceImp implements OrderService{
	OrderDao orderDao=new OrderDaoImp();
	@Override
	public void saveOrder(Order order) throws Exception {
		// 保存订单和订单下所有的订单项（同时成功，失败）
//		try {
//			JDBCUtils.startTransaction();
//			OrderDao orderDao=new OrderDaoImp();
//			orderDao.saveOrder(order);
//			for (OrderItem item : order.getList()) {
//				orderDao.saveOrderItem(item);
//			}
//			JDBCUtils.commitAndClose();
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			JDBCUtils.rollbackAndClose();
//		}
		Connection conn=null;
		try {
			//获取链接
			conn=JDBCUtils.getConnection();
			//开启事务
			conn.setAutoCommit(false);
			//保存订单
			
			orderDao.saveOrder(conn,order);
			//保存订单项
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn,item);
			}
			//提交
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			conn.rollback();
			}
		}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		// 1创建pagemodel对象，且计算并携带分页参数
		int totalRecords=orderDao.getTatalRecords(user);
		PageModel pm=new PageModel(curNum, totalRecords, 3);
		// 2关联集合
		List list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		// 3关联url
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		           
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		
		return orderDao.findOrderByOid(oid);
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.updateOrder(order);
	}

	@Override
	public List<Order> findAllOrders() throws Exception {
		return orderDao.findAllOrders();
	}

	@Override
	public List<Order> findAllOrders(String st) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.findAllOrders(st);
	}
	

}
