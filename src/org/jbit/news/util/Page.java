package org.jbit.news.util;

import java.util.List;

import org.jbit.news.entity.Newst;

public class Page {
	private int totalPageCount=0,pageSize=5,totalCount,currPageNo=1;
	
	private List<Newst> newsList;

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize>0) {
			this.pageSize = pageSize;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount>0) {
			this.totalCount = totalCount;
			//
			totalPageCount=this.totalCount%pageSize==0?(this.totalCount/pageSize):(this.totalCount/pageSize+1);			
		}
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		if (currPageNo>0) {
			this.currPageNo = currPageNo;
		}
	}

	public List<Newst> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<Newst> newsList) {
		this.newsList = newsList;
	}
	
	
}
