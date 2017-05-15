package com.shhege.core.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 单例模式存放数据，作为缓存使用
 * @author HaoXijun
 *
 */
public class CacheData {
	
	private CacheData(){};
	
	public static CacheData singleton = null;
	
	/*
	 * 记录缓存记录时间
	 */
	private static Map<String, Date> dataPutinTime = null;
	
	/*
	 * 记录缓存时间
	 */
	private static Map<String, Long> dataLiftTime = null;
	
	/*
	 * 记录缓存数据值
	 */
	private static Map<String, Object> dataValue = null;
	
	/*
	 * 得到单例
	 */
	public static synchronized CacheData getInstance(){
		if(singleton == null) {
			singleton = new CacheData();
			singleton.dataValue = new HashMap<String, Object>();
			singleton.dataPutinTime = new HashMap<String, Date>();
			singleton.dataLiftTime = new HashMap<String, Long>();
		}
		return singleton;
	}
	
	/**
	 * 获取单例数据
	 * @param key
	 * @return
	 */
	public synchronized Object getData(String key){
		Object data = null;
		if(key != null && !"".equals(key)) {
			if(dataValue.get(key) != null && dataPutinTime.get(key) != null) {
				if(dataLiftTime.get(key) == null) {
					data = dataValue.get(key);
				} else if(new Date().getTime()-dataPutinTime.get(key).getTime() <= dataLiftTime.get(key)) {
					data = dataValue.get(key);
				}
			}
		}
		return data;
	}
	
	/**
	 * 存放无限期的缓存
	 * @param key
	 * @param value
	 */
	public synchronized void putData(String key, Object value){
		dataValue.put(key, value);
		dataPutinTime.put(key, new Date());
		dataLiftTime.put(key, null);
	}
	
	/**
	 * 存放规定嫌弃的缓存，缓存时间单位为毫秒  若存放1小时：1*60*60*1000
	 * @param key
	 * @param value
	 * @param lift
	 */
	public synchronized void putData(String key, Object value, long lift) {
		dataValue.put(key, value);
		dataPutinTime.put(key, new Date());
		dataLiftTime.put(key, lift);
	}
}
