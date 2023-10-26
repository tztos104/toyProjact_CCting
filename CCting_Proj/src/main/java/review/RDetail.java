package review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RBoardDAO;
import service.RBoardService;

public class RDetail implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle","게시판 상세");
		System.out.println("RDetail.execute() 실행");
		
		int id = Integer.parseInt(request.getParameter("id"));
		new RBoardDAO().addCount(id);
		request.setAttribute("mainData",new RBoardDAO().detail(id));
	}
}
