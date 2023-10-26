package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nonJsp/*")
public class NonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NonController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String serviceStr = request.getRequestURI().substring((request.getContextPath()+"/nonJsp/").length());
		System.out.println(serviceStr);
		
		try {
			DistService service = (DistService)Class.forName("Dist."+serviceStr).newInstance();
			service.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
