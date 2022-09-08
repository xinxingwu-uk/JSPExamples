package com.product;

import java.util.List;
import java.util.Map;

public interface ProductService {
	public boolean addProduct(List<Object> params);
	
	//列出产品,为了分页，加上参数 start,end
	public List<Map<String, Object>> listProduct(String proname , int start , int end);
	//获取总的记录数
	public int getItemCount(String proname);
	//批处理删除产品
	public boolean delProduct(String[] ids);
	//查询单个产品
	public Map<String, Object> viewProduct(String proid);
}