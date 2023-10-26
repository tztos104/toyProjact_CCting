package model_p;

import javax.servlet.http.HttpServletRequest;

public class PageData {
	
	// 한 페이지당 글의 수
	int limit = 3;
	// 페이징 범위
	int pageLimit = 4;

	// 현재 몇페이지인지, 페이지당 시작번호, 페이지 범위의 시작, 페이지 범위의 마지막, 총 게시글 수, 총 페이지수
	public int page, start, pageStart, pageEnd, total, pageTotal;
	
	public PageData(HttpServletRequest request) {
		page = 1;
		// 파라미터를 통해 넘어온 페이지 값이 있다면 그 값을 페이지로 함
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
	}
	
	public void calc() {
		// total은 boardDAO의 총 게시글 수를 가져옴
		this.total = new ArtsDAO().totalCnt();
		
		// 총 페이지 수 = 총 게시글 / 페이지당 글 수
		pageTotal = total / limit;
		// 총 게시글 / 페이지당 글 수가 나머지가 존재한다면(나누어 떨어지지 않으면)
		if(total % limit > 0) {
			// 총 페이지 수에 1을 더함
			pageTotal++;
		}
		
		// 현재 페이지 시작번호 = (현재 페이지-1)*페이지당 게시글 수
		start = (page-1)*limit;
		// 페이지 범위의 시작 = ((현재 페이지-1) / 페이징범위) * 페이징범위 +1
		pageStart = (page-1) / pageLimit * pageLimit + 1;
		// 페이지 범위의 끝 = 페이지 범위의 시작 + 페이징범위 -1
		pageEnd = pageStart + pageLimit - 1;
		
		// 페이지 범위의 끝이 전체 페이지 수보다 크게 나온다면
		if(pageEnd > pageTotal) {
			// 페이지 범위의 끝을 전체 페이지 수로 줄여줌
			pageEnd = pageTotal;
		}
	}

}
