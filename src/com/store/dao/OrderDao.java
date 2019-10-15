package com.store.dao;

import java.sql.Connection;
import java.util.List;

import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.User;

public interface OrderDao {

	void saveOrder(Connection conn, Order order)throws Exception;

	void saveOrderItem(Connection conn, OrderItem item)throws Exception;

	int getTatalRecords(User user)throws Exception;

	List findMyOrdersWithPage(User user, int startIndex, int pageSize)throws Exception;

	Order findOrderByOid(String oid)throws Exception;

	void updateOrder(Order order)throws Exception;

	List<Order> findAllOrders()throws Exception;

	List<Order> findAllOrders(String st)throws Exception;

}
