package com.shhege.service.dao;

import java.util.List;

import com.shhege.madel.po.NewsInfoPo;

public interface NewsInfoDao {
	
	/**
	 * 查询所有新闻信息的总记录条数
	 * @return
	 */
	public int countOfNewsInfo();
	
	/**
	 * 分页查询新闻
	 * @param start
	 * @param count
	 * @return
	 */
	public List<NewsInfoPo> findNewsInfoByPage(int start, int count);
	
	/**
	 * 根据id查询新闻详情
	 * @param id
	 * @return
	 */
	public NewsInfoPo findNewsInfoPoById(String id);
	
	/**
	 * 根据新闻id更新新闻信息
	 * @param newsInfoPo
	 * @return
	 */
	public int updateNewsInfoById(NewsInfoPo newsInfoPo);
	
	/**
	 * 插入新闻信息
	 * @param newsInfoPo
	 * @return
	 */
	public String insertNewsInfo(NewsInfoPo newsInfoPo);

}
