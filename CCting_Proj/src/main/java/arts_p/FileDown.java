package arts_p;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.BoardService;

//보드서비스 인터페이스 상속 - execute 재정의 의무
public class FileDown implements BoardService {
	
	// execute 재정의
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 파일이름을 가져옴
		String fName = request.getParameter("fName");

//		String path = request.getRealPath("up");
		// 가상화된 주소가 떠버림 C:\\green_project\\jspWorks\\.metadata\\.plugins\\
		// org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\mvcProj\\up
//		System.out.println(path);
		// 실제 path를 부여
		// 학원
		String path = "C:\\CCting\\CCting_Proj\\src\\main\\webapp\\up";
		// 놋북
//		String path = "C:\\coding0\\CCting\\CCting_Proj\\src\\main\\webapp\\up";

		try {
			// FileInputStream: 파일 다운로드를 처리하기 위해 웹 서버의 파일 시스템에서 파일을 읽어올 때 사용
			// path에서 파일이름으로 파일을 찾음
			FileInputStream fis = new FileInputStream(path+"\\"+fName);
			// 인코딩
			String encFName = URLEncoder.encode(fName,"utf-8");
			
			// 헤더에 파일이름을 보냄
			response.setHeader("Content-Disposition", "attachment;filename="+encFName);
			// 파일저장이 뜨지만 파일에 내용이 없어 용량이 0kb
			
			// ServletOutputStream: 바이트 단위로 클라이언트로 데이터를 보내는 기능을 제공
			// output으로 출력, data를 클라이언트에게 전달
			ServletOutputStream sos = response.getOutputStream();
			
			byte [] buf = new byte[1024];	// 한번에 1바이트씩 여러 번 전송 (제일 정보량이 지나갈 수 있는 통로가 작을 때를 기준으로 정해준 것)
			while(fis.available()>0) {	// 읽어올 정보가 남아있다면
				int len = fis.read(buf);	// 배열크기(바이트단위) = 읽어서 buf배열에 넣음
				sos.write(buf, 0, len);		// buf의 0부터 len만큼씩 보냄
			}
			// data전달 종료
			sos.close();
			// 파일찾기 종료
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
