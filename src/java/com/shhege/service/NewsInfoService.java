package com.shhege.service;

import org.springframework.transaction.annotation.Transactional;

import com.shhege.madel.page.Page;
import com.shhege.madel.po.NewsInfoPo;


public interface NewsInfoService {
	
	/**
	 * 根据新闻id获取新闻内容
	 * @param id
	 * @return
	 */
	abstract public NewsInfoPo detailOfNews(String id);
	
	/**
	 * 根据分页查询新闻页面
	 * @param pageIndex
	 * @param count
	 * @return
	 */
	abstract public Page<NewsInfoPo> listOfNews(int pageIndex, int count);
	
	/**
	 * 发布新闻
	 * @return
	 */
	@Transactional
	abstract public String publishNews(NewsInfoPo newsInfoPo);
	
	/**
	 * 更新新闻内容
	 * @param newsInfoPo
	 * @return
	 */
	@Transactional
	abstract public int updateNews(NewsInfoPo newsInfoPo);
}
