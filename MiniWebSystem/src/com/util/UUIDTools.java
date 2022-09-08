package com.util;

import java.util.UUID;

public class UUIDTools {

	public UUIDTools() {
		// TODO Auto-generated constructor stub
	}
	
	/**返回一个 6位的字符串
	 * @return
	 */
	public static String getUUID(){
		
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").substring(0, 6); 
		
	}

}