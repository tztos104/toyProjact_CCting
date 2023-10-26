package dist;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import controller.DistService;
import model.DistDAO;
import model.PageData;


public class DList implements DistService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle", "작품 목록");
		System.out.println("DList.execute() 실행");
		
		PageData pd = (PageData)request.getAttribute("pd");
		pd.calc();
		
		request.setAttribute("mainData", new DistDAO().list(pd));
	}

}
