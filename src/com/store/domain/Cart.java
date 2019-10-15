package com.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//2����3������map������
public class Cart {

	
	//�ܼ�/����
	private double total=0;
	//������ȷ���Ĺ�����
	Map<String,CartItem> map=new HashMap<String,CartItem>();
	//��ӹ�������ﳵ
	//���û�������빺�ﳵ��ť�����Խ���ǰҪ�������Ʒid����Ʒ�������͵�����ˣ�����˸�����Ʒid��ѯ����Ʒ��Ϣ
	//������Ʒ��ϢProduct��������Ҫ������Ʒ��������ǰ�Ĺ�����Ҳ�Ϳ��Ի�ȡ����
	public void addCartItemToCar(CartItem cartItem){
		//��ȡ�����ڹ��ﳵ����ӵ���ƷPid
		String pid=cartItem.getProduct().getPid();
		//����ǰ�Ĺ�������빺�ﳵ֮ǰ���ж�֮ǰ�Ƿ����������Ʒ
		//���û�����list.add(cartItem)
		//����������ȡ��ԭ�ȵ���������ȡ��������������Ӻ����õ�ԭ�ȹ�������
		if(map.containsKey(pid)){
			CartItem oldItem=map.get(pid);
			oldItem.setNum(oldItem.getNum()+cartItem.getNum());
		}else{
			map.put(pid, cartItem);
		}
		//Ĭ��û�й������Ʒ
	}
	//����map�����е�ֵ
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	
	
	//�Ƴ�������
	public void removeCartItem(String pid){
		map.remove(pid);
	}
	//��չ��ﳵ
	public void clearCart(){
		map.clear();
	}
	public double getTotal() {
		total=0;
		//��ȡmap�����еĹ�����
		Collection<CartItem> values=map.values();
		//�������еĹ�������������ϵ�С�����
		for (CartItem cartItem : values) {
			total+=cartItem.getSubTotal();
		}
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
}
