package r_model;

import javax.servlet.http.HttpServletRequest;

public class PageData {

	// limit : 한 페이지에 보여줄 게시글 수
	// pageLimit : 한번에 보여지는 page 번호 수 
	public int limit = 10;
	public int pageLimit = 5;
	//int start = 0;
	public int page, start, pageStart, pageEnd, total, pageTotal;
	
	public PageData( HttpServletRequest request) {
		// 값이 없으면 1로 초기화
		page = 1;
		
		// BoardController에서 pageData값 가져옴 
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
			
		}
		System.out.println(page);
	}
	
	public void calc() {
		
		this.total = new RBoardDAO().totalCnt();
		
		pageTotal = total/limit;
		
		// limit(한페이지 출력되는 글의 수)로 나눴을때 나머지가 있으면 page의 숫자 한개더 추가
		if(total%limit>0) {
			pageTotal++;
		}
		
		// 페이지처리 공식
		// 글 시작 번호 : (page번호 -1) * limit     ( => BoardDAO에서 사용 )
		// 페이지 시작번호 : (page번호 -1) * limit   
		// 페이지 끝 번호 : (page번호 -1) / pageLimit * pageLimit + 1;
		start = (page-1)*limit;
		pageStart = (page-1)/pageLimit*pageLimit+1;
		pageEnd = pageStart + pageLimit -1;
		// 마지막 페이지 처리 (값이있는 페이지까지만 나오도록)
		if(pageEnd > pageTotal) {
			pageEnd = pageTotal;
		}
		
		System.out.println(pageTotal+","+pageEnd);
	}
	
	
	
	
	
}
