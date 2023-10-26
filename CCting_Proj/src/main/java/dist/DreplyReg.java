package dist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DistService;
import model.DistDAO;
import model.DistDTO;
import model.PageData;


public class DreplyReg implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BmodifyReg execute 실행 ");
		
		DistDTO dto = new DistDTO();
		PageData pd = (PageData)request.getAttribute("pd");
		
		
		dto.setDname(request.getParameter("dname"));
		dto.setDpw(request.getParameter("dpw"));
		dto.setDtitle(request.getParameter("dtitle"));
		dto.setDcontent(request.getParameter("dcontent"));
		dto.setGid(Integer.parseInt(request.getParameter("gid")));
		dto.setSeq(Integer.parseInt(request.getParameter("seq")));
		dto.setLev(Integer.parseInt(request.getParameter("lev")));
				
		new DistDAO().reply(dto);
		
		String msg = "작성이 완료되었습니다.";
		String goUrl = "Ddetail?id="+dto.getDid()+"&page="+pd.page;
		
		request.setAttribute("mainPage", "inc/alert");
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);

	}

}
