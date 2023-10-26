package model;

import javax.servlet.http.HttpServletRequest;

public class PageData {
	public int limit = 3;
	public int pageLimit = 4;
	public int page, pageStart, pageEnd, start, total, pageTotal;
	
	public PageData(HttpServletRequest request) {
		page = 1;
		
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
	}
	
	public void calc() {
		this.total = new DistDAO().totalCnt();
		pageTotal = this.total/limit;
		
		if(this.total%limit>0) {
			pageTotal++;
		}
		
		start = (page-1)*limit;
		pageStart = (page - 1) / pageLimit * pageLimit + 1;
		pageEnd = pageStart + pageLimit - 1;			
		
		if(pageEnd > pageTotal) {
			pageEnd = pageTotal;
		}
	}
	public int getPage() {
		return page;
	}
	public int getPageStart() {
		return pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public int getStart() {
		return start;
	}
}
