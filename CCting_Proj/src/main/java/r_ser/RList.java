package r_ser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r_control.RBoardService;
import r_model.PageData;
import r_model.RBoardDAO;


public class RList implements RBoardService{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("mainTitle","리뷰 게시판 목록");
		
		PageData pd = (PageData)request.getAttribute("pd");
		pd.calc();
		System.out.println("RList.execute() 실행:"+pd.page);

		request.setAttribute("mainData",new RBoardDAO().list(pd));
	}
}
