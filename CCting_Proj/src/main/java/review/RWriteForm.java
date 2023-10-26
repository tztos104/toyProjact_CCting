package review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RBoardService;

public class RWriteForm implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("mainTitle","게시판 글쓰기");
		System.out.println("RList.execute() 실행");

	}

}
