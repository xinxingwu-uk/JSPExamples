package com.util;

public class DividePage {

	private int pageSize ; //ÿһҳ�ļ�¼��
	private int totalRecord;//�ܼ�¼��
	private int currentPage;//��ǰ�ڼ�ҳ
	public DividePage(int pageSize, int totalRecord, int currentPage) {		
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		setCurrentPage(currentPage);		
		
	}
	public DividePage(int pageSize, int totalRecord) {		
		this(pageSize,totalRecord,1);	
		
	}
	
	//��ȡ��ҳ��
	public int getPageCount(){		
		int pageCount = totalRecord/pageSize;
		int mod = totalRecord%pageSize;
		if (mod!=0) {
			pageCount++;
		}		
		return pageCount;		
	}
	
	// mysql : select * from product limit 5,10  ��ʾ��ѯ��¼�� ��6��15�С�
	
	//��ʼ��¼�ӵڼ��п�ʼ(mysql ��¼Ĭ�ϴӵ�0�п�ʼ)
	public int fromIndex(){
		
		return (currentPage-1)*pageSize;
	}
	
	//Ҫ��ѯ�ĵ�β��¼�������ʼ��¼��ƫ��������һҳ�ļ�¼��
	public int toIndex(){
		return pageSize;
	}
	
	public void setCurrentPage( int currentPage){		
		
		if (getPageCount()!=0) {//�м�¼
			
			int validPage = currentPage<1?1:currentPage;
			validPage = validPage>getPageCount()?getPageCount():validPage;		
			this.currentPage = validPage;
		} else{ // 0����¼
			this.currentPage = 1;
		}
		
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
	
}