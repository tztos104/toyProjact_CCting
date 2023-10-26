package r_control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import r_model.PageData;



@WebServlet("/review/*")
public class RBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RBoardController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serviceStr = request.getRequestURI().substring(
				(request.getContextPath()+"/review/").length()
				);
		System.out.println(request.getRequestURI());
		
		System.out.println(serviceStr);

		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("mainPage", "review/"+serviceStr);
			request.setAttribute("pd", new PageData(request));
			
			RBoardService service = (RBoardService)Class.forName("r_ser."+serviceStr).newInstance();
			service.execute(request,response);
			
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/views/template.jsp");
			
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
