package com.shhege.core.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

public class DateUtil {
	
	final static public SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	final static public SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static public Date parse(String date){
		try {
			if(StringUtils.isBlank(date)){
				return null;
			} else if(date.length() == 10){
				return sdfDate.parse(date);
			} else if(date.length() == 19){
				return sdfDateTime.parse(date);
			} else {
				throw new IllegalArgumentException("日期字符串格式不正确，不能转换 格式只能：yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static public String parse(Date date){
		return sdfDate.format(date).substring(0, 10);
	}
	
	static public String parseDatetime(Date date){
		return sdfDateTime.format(date);
	}
}
