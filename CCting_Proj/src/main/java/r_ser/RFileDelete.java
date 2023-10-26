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


public class RFileDelete implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String path = request.getRealPath("up");
		path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		PageData pd = (PageData)request.getAttribute("pd");
		
		try {
			MultipartRequest mr = new MultipartRequest(
					request,
					path,
					10*1024*1024,
					"utf-8",
					new DefaultFileRenamePolicy()
				);
			
			RBoardDTO dto = new RBoardDTO();
			dto.setId( Integer.parseInt(mr.getParameter("id")));
			dto.setRtype(mr.getParameter("rtype"));
			dto.setTitle( mr.getParameter("title"));
			dto.setNic( mr.getParameter("nic"));
			dto.setPw( mr.getParameter("pw"));
			dto.setContent( mr.getParameter("content"));
			dto.setUpfile( mr.getFilesystemName("upfile"));
			
			String msg = "비밀번호가 일치하지 않습니다.";
			String goUrl = "RModifyForm?id="+dto.getId()+"&page="+pd.page;
			
			System.out.println(dto);
			
			
			
			RBoardDTO delDto = new RBoardDAO().idPwChk(dto);
			
			if(delDto!=null) {//id, pw 가 일치한다면
				
				
				//파일 삭제
				new File(path+"\\"+delDto.getUpfile()).delete();
				new RBoardDAO().fileDelete(dto);
				
				msg = "파일 삭제되었습니다.";
				//goUrl = "BList";
			}
			
					
			request.setAttribute("mainPage", "alert");
			request.setAttribute("msg",msg);
			request.setAttribute("goUrl",goUrl);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
	}

}
