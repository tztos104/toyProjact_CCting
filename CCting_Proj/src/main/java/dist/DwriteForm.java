package dist;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import controller.DistService;

public class DwriteForm implements DistService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle", "게시판 작성");
		System.out.println("DwriteForm.execute() 실행");
	}

}
