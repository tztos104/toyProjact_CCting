package r_ser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r_control.RBoardService;
import r_model.RBoardDAO;

public class RModifyForm implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle","게시판 수정");
		System.out.println("RModifyForm.execute() 실행");
		
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("mainData",new RBoardDAO().detail(id));
	}

}
