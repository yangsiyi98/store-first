package com.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.Imp.ProductServiceImp;
import com.store.web.base.BaseServlet;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	//��ӹ�������ﳵ
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		//��session���ȡ���ﳵ
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		if(cart==null){
			//�����ȡ�������������ﳵ����
			cart=new Cart();
			req.getSession().setAttribute("cart", cart);
		}
			//�����ȡ����ֱ��ʹ��
			//��ȡ����Ʒid������
			String pid=req.getParameter("pid");
			int num=Integer.parseInt(req.getParameter("quantity"));
			//ͨ����Ʒid��ѯ��Ʒ����
			ProductService ProductService=new ProductServiceImp();
			Product product=ProductService.findProductByPid(pid);
			//��ȡ��������Ĺ�����
			CartItem cartItem=new CartItem();
			cartItem.setNum(num);
			cartItem.setProduct(product);
			
			//���ù��ﳵ�ϵķ���
			cart.addCartItemToCar(cartItem);
			//�ض���cart.jsp
			resp.sendRedirect("/store/jsp/cart.jsp");
		
		return null;
	}
	
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//��ȡ��ɾ����Ʒpid
		String pid=req.getParameter("id");
		//��ȡ���ﳵ
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		//���ù��ﳵɾ���������
		cart.removeCartItem(pid);
		//�ض���cart.jsp
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
		
	}
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//��ȡ���ﳵ
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		//���ù��ﳵ�е���չ��ﳵ����
		cart.clearCart();
		//�ض���
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
		
	}

}
