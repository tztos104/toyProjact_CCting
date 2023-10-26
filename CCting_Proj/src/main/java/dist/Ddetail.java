package dist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DistService;
import model.DistDAO;



public class Ddetail implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle", "게시판 상세보기");
		System.out.println("DDetail.execute() 실행");
		
		int did = Integer.parseInt(request.getParameter("did"));
		new DistDAO().addCount(did);
		request.setAttribute("mainData", new DistDAO().detail(did));
	}

}
