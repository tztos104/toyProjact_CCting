package arts_p;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;
import model_p.ArtsDAO;
import model_p.ArtsDTO;
import model_p.PageData;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class ADeleteReg implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 게시글 모델 생성
		ArtsDTO dto = new ArtsDTO();
		
		PageData pd = (PageData)request.getAttribute("pd");
		
		// id와 pw 세팅
		dto.setId(Integer.parseInt(request.getParameter("id")));
		dto.setPw(request.getParameter("pw"));
		
		
		// id, pw 검사 및 파일정보 가져오기
		// id, pw 검사 실패값으로 초기화
		String msg = "비밀번호가 일치하지 않습니다.";
		String goUrl = "ADeleteForm?id="+dto.getId()+"&page="+pd.page;
		
		// 게시글 모델 dto에 idPwChk메서드를 호출해서 delDto로 만듦
		ArtsDTO delDto = new ArtsDAO().idPwChk(dto);
		// 실제 path를 부여
		// 학원
		String path = "C:\\CCting\\CCting_Proj\\src\\main\\webapp\\up";
		// 놋북
//		String path = "C:\\coding0\\CCting\\CCting_Proj\\src\\main\\webapp\\up";
		if(delDto!=null) {	// id, pw가 일치한다면(검색한 결과가 있다면)
			if(!delDto.getPhoto1().equals("")) {	// 파일이 존재한다면
				// path에서 파일을 삭제
				new File(path+"\\"+delDto.getPhoto1()).delete();
			}
			if(!delDto.getPhoto2().equals("")) {	// 파일이 존재한다면
				// path에서 파일을 삭제
				new File(path+"\\"+delDto.getPhoto2()).delete();
			}
			if(!delDto.getBfile1().equals("")) {	// 파일이 존재한다면
				// path에서 파일을 삭제
				new File(path+"\\"+delDto.getBfile1()).delete();
			}
			if(!delDto.getBfile2().equals("")) {	// 파일이 존재한다면
				// path에서 파일을 삭제
				new File(path+"\\"+delDto.getBfile2()).delete();
			}
			// 일치하는 결과를 db에서 삭제
			new ArtsDAO().delete(dto);
			// id, pw 검사 성공값 설정
			msg = "삭제되었습니다.";
			goUrl = "AList?page="+pd.page;
		}
		
		// 리다이렉트 하기위해 alert로
		request.setAttribute("mainPage", "inc/alert");
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
	}
}
