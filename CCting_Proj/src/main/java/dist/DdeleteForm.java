package dist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DistService;

public class DdeleteForm implements DistService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle", "게시글 삭제");
		System.out.println("DdeleteForm.execute() 실행");
	}

}