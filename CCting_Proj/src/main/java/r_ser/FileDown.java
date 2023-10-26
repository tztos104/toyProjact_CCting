package r_ser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r_control.RBoardService;

public class FileDown implements RBoardService{
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String fName = request.getParameter("fName");
		String path = request.getRealPath("up");
		path = "D:\\CCting_ProJ\\CCting_Proj\\src\\main\\webapp\\up";
		
		
		try {
			// 서버에서 파일가져오는애
			FileInputStream fis = new FileInputStream(path+"\\"+fName);
			String encFName = URLEncoder.encode(fName,"utf-8");
			System.out.println(fName+"->"+encFName);
			response.setHeader("Content-Disposition", "attachment;filename="+encFName);
			
			// 파일 보내는애 서버에서 클라이언트한테 데이터 주는애 
			ServletOutputStream sos = response.getOutputStream();
			
			byte [] buf = new byte[1024];
			
			//int cnt = 0;
			while(fis.available()>0) { //읽을 내용이 남아 있다면
				int len = fis.read(buf);  //읽어서 -> buf 에 넣음
											//len : 넣은 byte 길이
				
				sos.write(buf, 0, len); //보낸다 :  buf의 0부터 len 만큼
				
				//cnt ++;
				//System.out.println(cnt+":"+len);
			}
			
			sos.close();
			fis.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
	}

}
