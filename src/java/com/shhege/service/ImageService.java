package com.shhege.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.shhege.madel.po.ImgInfoPo;
import com.shhege.madel.vo.ImgVo;

public interface ImageService {
	
	/**
	 * 阿里云OSS 图片上传
	 * @param file
	 * @return
	 */
	@Transactional
	abstract public ImgVo uploadImgOnOss(MultipartFile file);
	
	/**
	 * 阿里云OSS 根据图片名称获取图片url
	 * @param imgId
	 * @return
	 */
	abstract public String getImgUrlOnOss(String imgName);
	
	/**
	 * 本地服务器取得图片信息
	 * @param imgId
	 * @return
	 */
	abstract public String getImgUrlById(String imgId);
	
	/**
	 * 根据imgId获取图片名称和URL
	 * @param imgId
	 * @return
	 */
	abstract public ImgInfoPo detailImg(String imgId);
}
