package r_ser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r_control.RBoardService;

public class RDeleteForm implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("mainTitle","게시판 삭제");
		System.out.println("RDelete.execute() 실행");

	}

}
