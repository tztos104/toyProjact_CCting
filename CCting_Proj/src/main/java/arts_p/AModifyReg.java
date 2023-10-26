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

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class AModifyReg implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 실제 path를 부여
		// 학원
		String path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		// 놋북
//		String path = "C:\\coding0\\CCting\\CCting_Proj\\src\\main\\webapp\\up";
		
		PageData pd = (PageData)request.getAttribute("pd");
		
		try {
			// MultipartRequest: cos.jar를 통해 쓸 수 있는 클래스
			// 클라이언트에서 전송된 멀티파트요청(파일 업로드 또는 폼 데이터에 파일과 일반 텍스트 데이터가 혼합된 요청)을 처리
			MultipartRequest mr = new MultipartRequest(
						request, 
						path,
						10*1024*1024,
						"utf-8",
						// 같은 이름의 파일이 있다면 뒤에 숫자를 붙여서 저장
						new DefaultFileRenamePolicy()
					);
			
			// 게시글 모델 생성
			ArtsDTO dto = new ArtsDTO();
			// 글아이디 제목 작성자 비번 내용 파일 필드에서 정보를 가져와서 세팅
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
			
			// id, pw 검사
			// id, pw 검사 실패값으로 초기화
			String msg = "비밀번호가 일치하지 않습니다.";
			String goUrl = "AModifyForm?id="+dto.getId()+"&page="+pd.page;
			
			if(new ArtsDAO().modify(dto)>0) {	// id, pw가 일치한다면 1을 return
				// id, pw 검사 성공값 설정
				msg = "수정되었습니다.";
				goUrl = "ADetail?id="+dto.getId();
			}else{	// 이미 인스턴스를 만들면서 이미지가 업로드 되기 때문에 id, pw가 불일치하면 지워줘야 함
//				if(!dto.getUpfile().equals("")) {
				if(mr.getFilesystemName("photo1")!=null) {	// 폼에 파일이 저장되어있었다면
					// path에서 파일을 삭제
					new File(path+"//"+mr.getFilesystemName("photo1")).delete();
				}
				if(mr.getFilesystemName("photo2")!=null) {	// 폼에 파일이 저장되어있었다면
					// path에서 파일을 삭제
					new File(path+"//"+mr.getFilesystemName("photo2")).delete();
				}
				if(mr.getFilesystemName("bfile1")!=null) {	// 폼에 파일이 저장되어있었다면
					// path에서 파일을 삭제
					new File(path+"//"+mr.getFilesystemName("bfile1")).delete();
				}
				if(mr.getFilesystemName("bfile2")!=null) {	// 폼에 파일이 저장되어있었다면
					// path에서 파일을 삭제
					new File(path+"//"+mr.getFilesystemName("bfile2")).delete();
				}
			}
			
			// 리다이렉트 하기위해 alert로
			request.setAttribute("mainPage", "inc/alert");
			request.setAttribute("msg", msg);
			request.setAttribute("goUrl", goUrl);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
