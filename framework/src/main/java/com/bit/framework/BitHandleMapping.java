package com.bit.framework;

import java.util.HashMap;
import java.util.Map;

public class BitHandleMapping {
	Map<String,BitController> map=new HashMap<>();
	
	public void addUrl(String url,String info){
		try {
			Class clzz=Class.forName(info);
			BitController controller = (BitController) clzz.newInstance();
			map.put(url, controller);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public BitController getController(String url){
		return map.get(url);
	}
}
