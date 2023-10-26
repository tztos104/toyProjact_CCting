package dist;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DistService;
import model.DistDAO;
import model.DistDTO;
import model.PageData;


public class DdeleteReg implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BdeleteReg execute 실행 ");
		
		String path = request.getRealPath("up");
		path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		
		DistDTO dto = new DistDTO();
		PageData pd = (PageData)request.getAttribute("pd");
		
		dto.setDid(Integer.parseInt(request.getParameter("did")));
		dto.setDpw(request.getParameter("dpw"));
		
		String msg = "비밀번호가 일치하지 않습니다.";
		String goUrl = "DdeleteForm?did="+dto.getDid()+"&page="+pd.page;
		
		DistDTO delDTO = new DistDAO().idPwChk(dto);
		
		// id/pw 검사 및 파일정보 가져오기
		if(delDTO != null) { // id, pw가 일치하다면
			if(!delDTO.getDfile1().equals("")) { // 파일이 존재한다면
				new File(path+"\\"+delDTO.getDfile1()).delete();
			}
			if(!delDTO.getDfile2().equals("")) { // 파일이 존재한다면
				new File(path+"\\"+delDTO.getDfile2()).delete();
			}
			new DistDAO().delete(dto);				
			msg = "삭제되었습니다.";
			goUrl = "DList?page="+pd.page;
		}
		
		request.setAttribute("mainPage", "inc/alert");
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
	}

}
