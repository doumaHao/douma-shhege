package com.shhege.madel.page;

import java.util.List;

public class Page<T> {
	
	/*
	 * 总条数
	 */
	private int allCount;
	
	/*
	 * 总页数
	 */
	private int pageCount;
	
	/*
	 * 每页多少条记录
	 */
	private int count;
	
	/*
	 * 当前页数
	 */
	private int pageIndex;
	
	/*
	 * 上一页
	 */
	private int pagePre;
	
	/*
	 * 下一页
	 */
	private int pageNext;
	
	/*
	 * 当前页记录详情
	 */
	private List<T> pageData;

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getPagePre() {
		return pagePre;
	}

	public void setPagePre(int pagePre) {
		this.pagePre = pagePre;
	}

	public int getPageNext() {
		return pageNext;
	}

	public void setPageNext(int pageNext) {
		this.pageNext = pageNext;
	}
}
