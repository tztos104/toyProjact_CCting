package r_ser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import r_control.RBoardService;
import r_model.RBoardDAO;
import r_model.RBoardDTO;

public class RWriteReg implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRealPath("up");
		path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		
		
		try {
			MultipartRequest mr = new MultipartRequest(
					request,
					path,
					10*1024*1024,
					"utf-8",
					new DefaultFileRenamePolicy()
				);
			
			RBoardDTO dto = new RBoardDTO();
			dto.setTitle(mr.getParameter("title"));
			dto.setNic(mr.getParameter("nic"));
			dto.setPw(mr.getParameter("pw"));
			dto.setContent(mr.getParameter("content"));
			dto.setUpfile(mr.getFilesystemName("upfile"));
			dto.setRtype(mr.getParameter("rtype"));
			
			new RBoardDAO().write(dto);
			
			System.out.println("newId:"+dto.getId());
			
			
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg","입력되었습니다.");
			request.setAttribute("goUrl","RDetail?id="+dto.getId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
	}

}
