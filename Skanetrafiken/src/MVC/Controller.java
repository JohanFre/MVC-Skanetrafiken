package MVC;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		@SuppressWarnings("unused")
		PrintWriter out = response.getWriter();
		
		
		ApiBean bean = new ApiBean();
		
		bean.setNames();
		
		bean.setDates();
		
		bean.setStations();
		
		bean.setId();
		
		request.setAttribute("bean", bean);
		
	
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");

		rd.forward(request, response);
	}
}
