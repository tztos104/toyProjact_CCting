package arts_p;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import control.BoardService;
import model_p.ArtsDAO;
import model_p.ArtsDTO;
import model_p.PageData;

public class AFileDelete implements BoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 학원
		String path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		// 놋북
//		String path = "C:\\coding0\\CCting\\CCting_Proj\\src\\main\\webapp\\up";
		
		PageData pd = (PageData)request.getAttribute("pd");
		
		try {
			MultipartRequest mr = new MultipartRequest(
					request,
					path,
					10*1024*1024,
					"utf-8",
					new DefaultFileRenamePolicy()
				);
			
			ArtsDTO dto = new ArtsDTO();
			dto.setId(Integer.parseInt(mr.getParameter("id")));
			dto.setName(mr.getParameter("name"));
			dto.setPw( mr.getParameter("pw"));
			dto.setAge(Integer.parseInt(mr.getParameter("age")));
			dto.setHeight(Integer.parseInt(mr.getParameter("height")));
			dto.setWeight(Integer.parseInt(mr.getParameter("weight")));
			dto.setAgency(mr.getParameter("agency"));
			dto.setArts(mr.getParameter("arts"));
			dto.setContent(mr.getParameter("content"));
			dto.setAwards(mr.getParameter("awards"));
			dto.setPhoto1(mr.getFilesystemName("photo1"));
			dto.setPhoto2(mr.getFilesystemName("photo2"));
			dto.setBfile1(mr.getFilesystemName("bfile1"));
			dto.setBfile2(mr.getFilesystemName("bfile2"));
			
			String msg = "비밀번호가 일치하지 않습니다.";
			String goUrl = "BModifyForm?id="+dto.getId()+"&page="+pd.page;
			
			System.out.println(dto);

			ArtsDTO delDto = new ArtsDAO().idPwChk(dto);
			
			if(delDto!=null) {//id, pw가 일치한다면
				if(!delDto.getPhoto1().equals("")) { //파일이 존재한다면
					//파일 삭제
					new File(path+"\\"+delDto.getPhoto1()).delete();
					new ArtsDAO().fileDelete(dto);
				}
				if(!delDto.getPhoto2().equals("")) { //파일이 존재한다면
					//파일 삭제
					new File(path+"\\"+delDto.getPhoto2()).delete();
					new ArtsDAO().fileDelete(dto);
				}
				if(!delDto.getBfile1().equals("")) { //파일이 존재한다면
					//파일 삭제
					new File(path+"\\"+delDto.getBfile1()).delete();
					new ArtsDAO().fileDelete(dto);
				}
				if(!delDto.getBfile2().equals("")) { //파일이 존재한다면
					//파일 삭제
					new File(path+"\\"+delDto.getBfile2()).delete();
					new ArtsDAO().fileDelete(dto);
				}
				msg = "파일 삭제되었습니다.";
				//goUrl = "BList";
			}
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg",msg);
			request.setAttribute("goUrl",goUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
