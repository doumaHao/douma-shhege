package com.shhege.constroller.alioss;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shhege.madel.vo.ImgVo;
import com.shhege.service.ImageService;

@Controller
public class AliOssConstroller {
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/imgUpload.json", method = RequestMethod.POST)
	@ResponseBody
	public ImgVo imgUpload(HttpServletRequest request,HttpServletResponse response, MultipartFile file){
		
		System.out.println("图片上传附加信息：" + request.getParameter("user"));
		
		ImgVo imgVo = new ImgVo();
		imgVo.setResult(false);
		
		try {
			imgVo = imageService.uploadImgOnOss(file);
			imgVo.setResult(true);
			String[] imgUrls = urlHandle(imgVo.getImgUrl());
			imgVo.setImgUrl1(imgUrls[0]);
			imgVo.setImgUrl2(imgUrls[1]);
			imgVo.setImgUrl3(imgUrls[2]);
		} catch (Exception e) {
			e.printStackTrace();
			imgVo.setResult(false);
		}
		return imgVo;
	}
	
	@RequestMapping(value = "/loadImgUrl.json", method = RequestMethod.GET)
	public void loadImgUrl(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		String imgId = request.getParameter("imgId");
		String imgUrl = imageService.getImgUrlOnOss(imgId);
		String[] imgUrls = urlHandle(imgUrl);
		jsonMap.put("imgUrl1", imgUrls[0]);
		jsonMap.put("imgUrl2", imgUrls[1]);
		jsonMap.put("imgUrl3", imgUrls[2]);
		try {
			response.setContentType("application/json");
	        response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out= null;
	        out = response.getWriter();
	        ObjectMapper mapper = new ObjectMapper(); 
	        out.print(mapper.writeValueAsString(jsonMap));
	        out.flush();
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//http://shhege.oss-cn-shanghai.aliyuncs.com/data/1491620416320.png?Expires=1806980416&OSSAccessKeyId=LTAIUwCHHejtv6Qx&Signature=fYZKmvvcHyYd/8GP%2BctrccyvnDo%3D
	private String[] urlHandle(String orgUrl){
		String[] lastUrl = new String[3];
		String[] orgUrls = orgUrl.split("Expires=");
		lastUrl[0] = orgUrls[0] + "Expires=";
		String[] orgUrls2 = orgUrls[1].split("&OSSAccessKeyId");
		lastUrl[1] = orgUrls2[0]+"&OSSAccessKeyId";
		lastUrl[2] = orgUrls2[1];
		
		return lastUrl;
	}

}
