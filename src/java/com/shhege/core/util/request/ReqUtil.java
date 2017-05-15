package com.shhege.core.util.request;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class ReqUtil {
	
	final static public String Req_Method_Type_Post = "POST";
	final static public String Req_Method_Type_Get = "GET";
	
	static public String getParam(HttpServletRequest request, String key){
		try {
			String param = request.getParameter(key);
			if(StringUtils.isNotBlank(param)){
				if(Req_Method_Type_Post.equals(request.getMethod())){
					return new String(param.getBytes("utf-8"), "utf-8");
				} else if(Req_Method_Type_Get.equals(request.getMethod())) {
					return new String(param.getBytes("ISO-8859-1"), "utf-8");
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
