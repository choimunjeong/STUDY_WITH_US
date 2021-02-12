package personalstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personalstudy.model.service.PersonalStudyService;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet(name = "DeleteTask", urlPatterns = { "/deleteTask" })
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int index = Integer.parseInt(request.getParameter("deleteIdx"));
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		int result = new PersonalStudyService().deleteTask(index,memberNo);
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.print(true);
		}
		else {
			out.print(false);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
