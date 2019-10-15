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
	//添加购物项到购物车
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {
		//从session里获取购物车
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		if(cart==null){
			//假如获取不到，创建购物车对象
			cart=new Cart();
			req.getSession().setAttribute("cart", cart);
		}
			//如果获取到，直接使用
			//获取到商品id，数量
			String pid=req.getParameter("pid");
			int num=Integer.parseInt(req.getParameter("quantity"));
			//通过商品id查询商品对象
			ProductService ProductService=new ProductServiceImp();
			Product product=ProductService.findProductByPid(pid);
			//获取到待购买的购物项
			CartItem cartItem=new CartItem();
			cartItem.setNum(num);
			cartItem.setProduct(product);
			
			//调用购物车上的方法
			cart.addCartItemToCar(cartItem);
			//重定向到cart.jsp
			resp.sendRedirect("/store/jsp/cart.jsp");
		
		return null;
	}
	
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取待删除商品pid
		String pid=req.getParameter("id");
		//获取购物车
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		//调用购物车删除购物项方法
		cart.removeCartItem(pid);
		//重定向到cart.jsp
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
		
	}
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取购物车
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		//调用购物车中的清空购物车方法
		cart.clearCart();
		//重定向
		resp.sendRedirect("/store/jsp/cart.jsp");
		return null;
		
	}

}
