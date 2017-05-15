package com.shhege.core.util.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;


public class PropertiesUtil {
	
	final static public String ossProperties = "alioss.properties";
	
	static public String getProperties(String name){
		String dir = PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		try {
			Properties prop = new Properties();
			InputStream in = new BufferedInputStream (new FileInputStream(dir+ossProperties));
			prop.load(in);
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key = it.next();
				if(StringUtils.equals(name, key)){
					System.out.println("你需要的"+name+"为："+prop.getProperty(name));
				}
				System.out.println(key+":"+prop.getProperty(key));
			}
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return name;
	}

}
