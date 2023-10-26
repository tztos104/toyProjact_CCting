package dist;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import model.DistDAO;
import model.PageData;
import service.DistService;


public class DList implements DistService {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mainTitle", "작품 목록");
		System.out.println("DList.execute() 실행");
		
		PageData pd = (PageData)request.getAttribute("pd");
		pd.calc();
		
		request.setAttribute("mainData", new DistDAO().list(pd));
	}

}
