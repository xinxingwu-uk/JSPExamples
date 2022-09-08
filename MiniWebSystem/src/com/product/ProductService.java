package com.product;

import java.util.List;
import java.util.Map;

public interface ProductService {
	public boolean addProduct(List<Object> params);
	
	//�г���Ʒ,Ϊ�˷�ҳ�����ϲ��� start,end
	public List<Map<String, Object>> listProduct(String proname , int start , int end);
	//��ȡ�ܵļ�¼��
	public int getItemCount(String proname);
	//������ɾ����Ʒ
	public boolean delProduct(String[] ids);
	//��ѯ������Ʒ
	public Map<String, Object> viewProduct(String proid);
}