package com.store.domain;

import java.util.List;
import java.util.ArrayList;

//2属性3方法（list方法）
public class Cart02 {
	//个数不确定的购物项
	private double total;
	//总计/积分
	private List<CartItem> list=new ArrayList();
	
	//添加购物项到购物车
	//当用户点击加入购物车按钮，可以将当前要购买的商品id，商品数量发送到服务端，服务端根据商品id查询到商品信息
	//有了商品信息Product对象，有了要购买商品数量，当前的购物项也就可以获取到了
	public void addCartItemToCart(CartItem cartItem){
		//将当前的购物项加入购物车之前，判断之前是否买过这类商品
		//如果没有买过list.add(cartItem)
		//如果买过：获取到原先的数量，获取到本次数量，相加后设置到原先购物项上
		//默认没有购买过商品
		boolean flag=false;
		//存储原先购物项
		CartItem old=null;
		for (CartItem cartItem2 : list) {
			if(cartItem2.getProduct().getPid().equals(cartItem.getProduct().getPid())){
				flag=true;
				old=cartItem2;
			}
		}
		if(flag==false){
			list.add(cartItem);
		}else{
			old.setNum(old.getNum()+cartItem.getNum());
			
		}
		
	}
	
	//移除购物项
	//用户点击移除购物项链接，可以将当前的购物类别的商品id发送到服务端
	public void removeCartItem(String pid){
		//遍历List,看每个CartItem上的product对象上的id是否和服务端获取到的pid相匹配，如果匹配则删除购物项
		for (CartItem cartItem : list) {
			if(cartItem.getProduct().getPid().equals(pid)){
				//删除当前的cartItem
				//直接调用list.remove（cartItem）;无法删除当前的cartItem,需要通过迭代器删除当前的购物项
			}
		}
	}
	//清空购物车
	public void clearCart(){
		list.clear();
	}
}
