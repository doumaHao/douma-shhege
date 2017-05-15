package com.shhege.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shhege.constroller.alioss.OSSClientUtil;
import com.shhege.core.util.id.IdGen;
import com.shhege.madel.po.ImgInfoPo;
import com.shhege.madel.vo.ImgVo;
import com.shhege.service.ImageService;
import com.shhege.service.dao.ImgInfoDao;

@Service("imageService")
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImgInfoDao imgInfoDao;
	
	@Autowired
	private OSSClientUtil ossClientUtil;

	@Override
	public ImgVo uploadImgOnOss(MultipartFile file) {
		ImgVo imgVo = new ImgVo();
		// 阿里云OSS上传图片
		String imgName = ossClientUtil.uploadImg2Oss(file);
		String imgUrl = getImgUrlOnOss(imgName);
		ImgInfoPo imgInfoPo = new ImgInfoPo();
		imgInfoPo.setId(IdGen.uuid());
		imgInfoPo.setImgId(IdGen.uulong());
		imgInfoPo.setImgName(imgName);
		imgInfoPo.setImgUrl(imgUrl);
		// 图片信息本地保存
		imgInfoDao.insertImg(imgInfoPo);
		imgVo.setImgId(imgInfoPo.getImgId());
		imgVo.setImgName(imgName);
		imgVo.setImgUrl(imgUrl);
		return imgVo;
	}

	@Override
	public String getImgUrlOnOss(String imgName) {
		return ossClientUtil.getImgUrl(imgName);
	}

	@Override
	public ImgInfoPo detailImg(String imgId) {
		return imgInfoDao.findImg(imgId);
	}

	@Override
	public String getImgUrlById(String imgId) {
		ImgInfoPo imgInfoPo = imgInfoDao.findImg(imgId);
		if(imgInfoPo != null){
			return imgInfoPo.getImgUrl();
		} else {
			return null;
		}
		
	}
}
