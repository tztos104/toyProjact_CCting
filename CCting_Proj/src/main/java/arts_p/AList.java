package arts_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;
import model_p.ArtsDAO;
import model_p.PageData;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class AList implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 헤더에 띄울 mainTitle 정보 보내기
		request.setAttribute("mainTitle","게시판 목록");
		
		PageData pd = (PageData)request.getAttribute("pd");
		pd.calc();

		// 클라이언트의 요청결과.발생한 데이터나 결과를 세팅함(메인데이터라는 이름으로, BoardDAO객체생성-list()메서드호출)
		/*
		 * ArrayList<BoardDTO> mainData = new BoardDAO().list();
		 * request.setAttribute("mainData", mainData);
		 */
		request.setAttribute("mainData", new ArtsDAO().list(pd));

	}

}
