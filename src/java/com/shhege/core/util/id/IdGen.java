package com.shhege.core.util.id;


import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author haoxijun
 * @version 2017-04-13
 */
@Component
public class IdGen {

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String uulong(){
		return String.valueOf(randomLong());
	};
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
}
