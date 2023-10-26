package r_ser;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import r_control.RBoardService;
import r_model.PageData;
import r_model.RBoardDAO;
import r_model.RBoardDTO;

public class RReplyReg implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		try {
		
			RBoardDTO dto = new RBoardDTO();
			PageData pd = (PageData)request.getAttribute("pd");
			
			dto.setRtype(request.getParameter("rtype"));
			dto.setTitle( request.getParameter("title"));
			dto.setNic( request.getParameter("nic"));
			dto.setPw( request.getParameter("pw"));
			dto.setContent( request.getParameter("content"));
			
			dto.setGid(Integer.parseInt(request.getParameter("gid")));
			dto.setLev(Integer.parseInt(request.getParameter("lev")));
			dto.setSeq(Integer.parseInt(request.getParameter("seq")));
			
			new RBoardDAO().reply(dto);
					
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg","작성되었습니다.");
			request.setAttribute("goUrl","RDetail?id="+dto.getId()+"&page="+pd.page);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
	}

}
