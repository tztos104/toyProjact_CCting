package dist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DistDAO;
import service.DistService;


public class DreplyForm implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle", "작품 답변");
		System.out.println("DreplyForm.execute() 실행");
		
		int did = Integer.parseInt(request.getParameter("did"));
		request.setAttribute("mainData", new DistDAO().detail(did));
	}

}
