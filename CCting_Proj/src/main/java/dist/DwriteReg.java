package dist;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.DistService;
import model.DistDAO;
import model.DistDTO;


public class DwriteReg implements DistService {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BwriteReg execute 실행 ");
		
		String path = request.getRealPath("up");
		path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, path, 10*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			DistDTO dto = new DistDTO();
			
			dto.setDname(mr.getParameter("dname"));
			dto.setDpw(mr.getParameter("dpw"));
			dto.setDtype(mr.getParameter("dtype"));
			dto.setDtitle(mr.getParameter("dtitle"));
			dto.setDcontent(mr.getParameter("dcontent"));
			dto.setDist(mr.getParameter("dist"));
			dto.setDphone(mr.getParameter("dphone"));
			dto.setDfile1(mr.getFilesystemName("dfile1"));
			dto.setDfile2(mr.getFilesystemName("dfile2"));
		
			
			
			
			new DistDAO().write(dto);
			
			System.out.println(dto.getDid());
			
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg", "작성이 완료되었습니다.");
			request.setAttribute("goUrl", "Ddetail?did="+dto.getDid());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
