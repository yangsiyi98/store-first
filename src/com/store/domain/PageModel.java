package com.store.domain;

import java.util.List;

/**
 * ��ŷ�ҳ��ص�����
 *
 */
public class PageModel {
	//��������
	private int currentPageNum;//��ǰҳ�������û�ָ��				*
	private int pageSize = 5 ;//ÿҳ��ʾ���������̶���				*
	private int totalRecords;//�ܼ�¼���������ݿ�������			    *
	private int totalPageNum;//��ҳ�������������					*
	private int startIndex;//ÿҳ��ʼ��¼�����������������			    *
	private int prePageNum;//��һҳ							    *
	private int nextPageNum;//��һҳ							    *
	
	private List list;//�Ѿ��ֺ�ҳ�Ľ����,��list��ֻ��10����¼
	
	
	
	//��չ����
	//һ��ÿҳ��ʾ9��ҳ�밴ť
	private int startPage;//��ʼҳ��
	private int endPage;//����ҳ��
	
	//��������
	private String url;
	
	


	//Ҫ��ʹ���ҵķ�ҳ�������������������һ����Ҫ����һҳ����һ�����ܼ�¼����
	public PageModel(int currentPageNum,int totalRecords,int pageSize){
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;
		this.pageSize=pageSize;
		
		//�����ѯ��¼�Ŀ�ʼ����
		startIndex = (currentPageNum-1)*pageSize;
		//������ҳ��
		totalPageNum = totalRecords%pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1);
		
		
		startPage = currentPageNum - 4; //5
		endPage = currentPageNum + 4;  //13
		//������ҳ��������9ҳ
		if(totalPageNum>9){
			//������9ҳ
			if(startPage < 1){
				startPage = 1;
				endPage = startPage+8;
			}
			if(endPage>totalPageNum){
				endPage = totalPageNum;
				startPage = endPage-8;
			}
		}else{
			//����9ҳ
			startPage = 1;
			endPage = totalPageNum;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrePageNum() {
		prePageNum = currentPageNum-1;
		if(prePageNum<1){
			prePageNum = 1;
		}
		return prePageNum;
	}

	public int getNextPageNum() {
		nextPageNum = currentPageNum+1;
		if(nextPageNum>totalPageNum){
			nextPageNum = totalPageNum;
		}
		return nextPageNum;
	}

	
	
	
	public int getCurrentPageNum() {
		return currentPageNum;
	}


	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}


	public int getTotalPageNum() {
		return totalPageNum;
	}


	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	


	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}


	

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}


	
}

