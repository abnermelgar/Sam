package gt.edu.altamira.jsf;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gt.edu.altamira.sam.jsf.JPAEntityPageBeanBase;




/**
 * Servlet implementation class CheckSession
 */
@WebServlet("/CheckSession")
public class CheckSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String res = "FAIL";
		if (session == null || session.getAttribute(JPAEntityPageBeanBase.LOGGED_USER) == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		}
		else{
			response.setStatus(HttpServletResponse.SC_OK);
			res = "OK";
		}
		
		response.setContentType("text/plain");
		response.setContentLength(res.length());
		response.getOutputStream().write(res.getBytes());
		
	}

}
