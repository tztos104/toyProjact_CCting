package dist;

import java.io.File;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import controller.DistService;
import model.DistDAO;
import model.DistDTO;
import model.PageData;


public class DFileDelete implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, path, 10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			DistDTO dto = new DistDTO();
			dto.setDid(Integer.parseInt(mr.getParameter("did")));
			dto.setDpw(mr.getParameter("dpw"));
			
			DistDTO delDTO = new DistDAO().idPwChk(dto);
			PageData pd = (PageData)request.getAttribute("pd");
			
			String msg = "비밀번호가 일치하지 않습니다.";
			String goUrl = "DmodifyForm?did="+dto.getDid()+"&page="+pd.page;
			
			if(delDTO != null) {
				new File(path+"\\"+delDTO.getDfile1()).delete();
				new File(path+"\\"+delDTO.getDfile2()).delete();
				new DistDAO().file1Delete(dto);
				new DistDAO().file2Delete(dto);
				
				msg = "파일이 삭제되었습니다.";
			}
			
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg", msg);
			request.setAttribute("goUrl", goUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
