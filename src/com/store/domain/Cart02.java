package com.store.domain;

import java.util.List;
import java.util.ArrayList;

//2����3������list������
public class Cart02 {
	//������ȷ���Ĺ�����
	private double total;
	//�ܼ�/����
	private List<CartItem> list=new ArrayList();
	
	//��ӹ�������ﳵ
	//���û�������빺�ﳵ��ť�����Խ���ǰҪ�������Ʒid����Ʒ�������͵�����ˣ�����˸�����Ʒid��ѯ����Ʒ��Ϣ
	//������Ʒ��ϢProduct��������Ҫ������Ʒ��������ǰ�Ĺ�����Ҳ�Ϳ��Ի�ȡ����
	public void addCartItemToCart(CartItem cartItem){
		//����ǰ�Ĺ�������빺�ﳵ֮ǰ���ж�֮ǰ�Ƿ����������Ʒ
		//���û�����list.add(cartItem)
		//����������ȡ��ԭ�ȵ���������ȡ��������������Ӻ����õ�ԭ�ȹ�������
		//Ĭ��û�й������Ʒ
		boolean flag=false;
		//�洢ԭ�ȹ�����
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
	
	//�Ƴ�������
	//�û�����Ƴ����������ӣ����Խ���ǰ�Ĺ���������Ʒid���͵������
	public void removeCartItem(String pid){
		//����List,��ÿ��CartItem�ϵ�product�����ϵ�id�Ƿ�ͷ���˻�ȡ����pid��ƥ�䣬���ƥ����ɾ��������
		for (CartItem cartItem : list) {
			if(cartItem.getProduct().getPid().equals(pid)){
				//ɾ����ǰ��cartItem
				//ֱ�ӵ���list.remove��cartItem��;�޷�ɾ����ǰ��cartItem,��Ҫͨ��������ɾ����ǰ�Ĺ�����
			}
		}
	}
	//��չ��ﳵ
	public void clearCart(){
		list.clear();
	}
}
