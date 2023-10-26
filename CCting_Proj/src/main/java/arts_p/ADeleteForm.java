package arts_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;

// 보드서비스 인터페이스 상속 - execute 재정의 의무
public class ADeleteForm implements BoardService {

	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 헤더에 띄울 mainTitle 정보 보내기
		request.setAttribute("mainTitle","게시글 삭제");
	}

}
