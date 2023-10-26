package dist;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import controller.DistService;
import model.DistDAO;

public class DmodifyForm implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle", "게시글 수정");
		System.out.println("DmodifyForm.execute() 실행");
		
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("mainData", new DistDAO().detail(id));
	}

}
