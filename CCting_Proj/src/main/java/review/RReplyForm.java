package review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RBoardDAO;
import service.RBoardService;

public class RReplyForm implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle","게시판 답변");
		System.out.println("RReplyForm 실행");
		
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("mainData",new RBoardDAO().detail(id));
	}

}
