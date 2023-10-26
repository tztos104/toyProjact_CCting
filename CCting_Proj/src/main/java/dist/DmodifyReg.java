package dist;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import controller.DistService;
import model.DistDAO;
import model.DistDTO;
import model.PageData;


public class DmodifyReg implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("DmodifyReg execute 실행 ");
		
		String path = request.getRealPath("up");
		path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, path, 10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			DistDTO dto = new DistDTO();
			PageData pd = (PageData)request.getAttribute("pd");
			
			dto.setDid(Integer.parseInt(mr.getParameter("did")));
			dto.setDname(mr.getParameter("dname"));
			dto.setDpw(mr.getParameter("dpw"));
			dto.setDtype(mr.getParameter("dtype"));
			dto.setDtitle(mr.getParameter("dtitle"));
			dto.setDcontent(mr.getParameter("dcontent"));
			dto.setDist(mr.getParameter("dist"));
			dto.setDphone(mr.getParameter("dphone"));
			dto.setDfile1(mr.getFilesystemName("dfile1"));
			dto.setDfile2(mr.getFilesystemName("dfile2"));
			dto.setOpenDate(mr.getParameter("openDate"));
			dto.setCloseDate(mr.getParameter("closeDate"));
			
			String msg = "비밀번호가 일치하지 않습니다.";
			String goUrl = "DmodifyForm?did="+dto.getDid()+"&page="+pd.page;
			
			// id/pw 검사 및 파일정보 가져오기
			if(new DistDAO().modify(dto) > 0) { // id, pw가 일치하다면
							
				msg = "수정되었습니다.";
				goUrl = "Ddetail?did="+dto.getDid()+"&page="+pd.page;
			}else {
				if(mr.getFilesystemName("dfile1") != null) {
					new File(path+"\\"+mr.getFilesystemName("dfile1")).delete();
				}
			}
			
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg", msg);
			request.setAttribute("goUrl", goUrl);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
