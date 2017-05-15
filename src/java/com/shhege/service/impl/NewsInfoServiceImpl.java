package com.shhege.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.shhege.core.util.id.IdGen;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.NewsInfoPo;
import com.shhege.madel.po.TextInfoPo;
import com.shhege.service.ImageService;
import com.shhege.service.NewsInfoService;
import com.shhege.service.TextInfoService;
import com.shhege.service.dao.NewsInfoDao;

public class NewsInfoServiceImpl implements NewsInfoService {
	
	@Autowired
	private NewsInfoDao newsInfoDao;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private TextInfoService textInfoService;

	@Override
	public NewsInfoPo detailOfNews(String id) {
		NewsInfoPo newsInfoPo = newsInfoDao.findNewsInfoPoById(id);
		//新闻图片
		String imgId = newsInfoPo.getNewsImg();
		if(StringUtils.isNotBlank(imgId)) {
			newsInfoPo.setNewsImg(imageService.getImgUrlOnOss(imageService.getImgUrlById(imgId)));;
		}
		//新闻内容
		String textId = newsInfoPo.getNewsContent();
		if(StringUtils.isNotBlank(textId)) {
			newsInfoPo.setNewsContent(textInfoService.getText(textId));
		}
		return newsInfoPo;
	}

	@Override
	public String publishNews(NewsInfoPo newsInfoPo) {
		//图片上传的时候，已经存入过图片表了，这次取得的就是imgId，所以不用再处理
		//新闻文本
		String text = newsInfoPo.getNewsContent();
		if(StringUtils.isNotBlank(text)) {
			TextInfoPo textInfoPo = new TextInfoPo();
			textInfoPo.setTextContent(text);
			String textId = textInfoService.insertText(textInfoPo);
			if(StringUtils.isNotBlank(textId)) {
				newsInfoPo.setNewsContent(textId);
			} else {
				return "";
			}
		}
		newsInfoPo.setId(IdGen.uuid());
		newsInfoPo.setPublishTime(new Date());
		String id = newsInfoDao.insertNewsInfo(newsInfoPo);
		if(StringUtils.isNotBlank(id)) {
			return id;
		} else {
			return "";
		}
	}

	@Override
	public int updateNews(NewsInfoPo newsInfoPo) {
		if(newsInfoPo == null 
				|| StringUtils.isBlank(newsInfoPo.getId())) {
			return 0;
		} else {
			//新闻文本
			String text = newsInfoPo.getNewsContent();
			if(StringUtils.isNotBlank(text)) {
				TextInfoPo textInfoPo = new TextInfoPo();
				textInfoPo.setTextContent(text);
				String textId = textInfoService.insertText(textInfoPo);
				if(StringUtils.isNotBlank(textId)) {
					newsInfoPo.setNewsContent(textId);
				} else {
					return 0;
				}
			}
			return newsInfoDao.updateNewsInfoById(newsInfoPo);
		}
	}

	@Override
	public Page<NewsInfoPo> listOfNews(int pageIndex, int count) {
		Page<NewsInfoPo> page = new Page<NewsInfoPo>();
		//总条数
		page.setPageCount(newsInfoDao.countOfNewsInfo());
		//总页数
		page.setPageCount((page.getPageCount()-1)/count+1);
		//每页最多多少条记录
		page.setCount(count);
		//当前页数
		page.setPageIndex(pageIndex);
		//当前记录集合
		page.setPageData(newsInfoDao.findNewsInfoByPage((pageIndex-1)*count, count));
		
		return page;
	}
}
