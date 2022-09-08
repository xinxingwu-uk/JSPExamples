package com.util;

public class DividePage {

	private int pageSize ; //每一页的记录数
	private int totalRecord;//总记录数
	private int currentPage;//当前第几页
	public DividePage(int pageSize, int totalRecord, int currentPage) {		
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		setCurrentPage(currentPage);		
		
	}
	public DividePage(int pageSize, int totalRecord) {		
		this(pageSize,totalRecord,1);	
		
	}
	
	//获取总页数
	public int getPageCount(){		
		int pageCount = totalRecord/pageSize;
		int mod = totalRecord%pageSize;
		if (mod!=0) {
			pageCount++;
		}		
		return pageCount;		
	}
	
	// mysql : select * from product limit 5,10  表示查询记录行 第6到15行。
	
	//起始记录从第几行开始(mysql 记录默认从第0行开始)
	public int fromIndex(){
		
		return (currentPage-1)*pageSize;
	}
	
	//要查询的的尾记录相对于起始记录的偏移量，即一页的记录数
	public int toIndex(){
		return pageSize;
	}
	
	public void setCurrentPage( int currentPage){		
		
		if (getPageCount()!=0) {//有记录
			
			int validPage = currentPage<1?1:currentPage;
			validPage = validPage>getPageCount()?getPageCount():validPage;		
			this.currentPage = validPage;
		} else{ // 0条记录
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