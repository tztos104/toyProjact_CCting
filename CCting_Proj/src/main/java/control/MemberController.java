package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
      public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath()); //Served at: /CCting_Proj 출력 확인
		
		
		String serviceStr = request.getRequestURI().substring(
				(request.getContextPath()+"/member/").length()
				);
		System.out.println(serviceStr);
		
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("mainPage", "member/"+serviceStr);
			
			MemberService service = 
					(MemberService)Class.forName("ser_p."+serviceStr).newInstance();
			service.execute(request, response);
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/views/template.jsp");
			
			dispatcher.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
