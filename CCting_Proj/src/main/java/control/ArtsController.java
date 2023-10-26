package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model_p.PageData;

@WebServlet("/arts/*")
public class ArtsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public ArtsController() {
        super();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serviceStr = request.getRequestURI().substring(
				(request.getContextPath()+"/arts/").length()
				);
		try {
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("mainPage", "arts/" + serviceStr);
			
			// 페이지처리를 하면서 PageData 정보를 넘겨줌
			request.setAttribute("pd", new PageData(request));
			
			BoardService service = (BoardService)Class.forName("arts_p."+serviceStr).newInstance();	// try catch
			service.execute(request, response);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/template.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
