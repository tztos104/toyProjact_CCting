package r_ser;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import r_control.RBoardService;
import r_model.PageData;
import r_model.RBoardDAO;
import r_model.RBoardDTO;

public class RDeleteReg implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	
		String path = request.getRealPath("up");
		path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";

		PageData pd = (PageData)request.getAttribute("pd");
		
			
		RBoardDTO dto = new RBoardDTO();
		dto.setId( Integer.parseInt(request.getParameter("id")));
		
		dto.setPw( request.getParameter("pw"));
		
		String msg = "비밀번호가 일치하지 않습니다.";
		String goUrl = "RDeleteForm?id="+dto.getId()+"&page="+pd.page;
		
		System.out.println(dto);
		//id/pw 검사 및 파일정보가져오기
		RBoardDTO delDto = new RBoardDAO().idPwChk(dto);
		
		if(delDto!=null) {//id, pw 가 일치한다면
			
			if(!delDto.getUpfile().equals("")) { //파일이 존재한다면
				//파일 삭제
				new File(path+"\\"+delDto.getUpfile()).delete();
			}
			//DB 에서 삭제
			new RBoardDAO().delete(dto);
			msg = "삭제되었습니다.";
			goUrl = "RList?page="+pd.page;
		}
				
		request.setAttribute("mainPage", "inc/alert");
		request.setAttribute("msg",msg);
		request.setAttribute("goUrl",goUrl);
		
		
	}

}
