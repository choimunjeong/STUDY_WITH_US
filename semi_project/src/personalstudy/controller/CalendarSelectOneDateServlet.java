package personalstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import personalstudy.model.service.PersonalStudyService;
import personalstudy.model.vo.PersonalStudyTask;

/**
 * Servlet implementation class CalendarSelectOneDateServlet
 */
@WebServlet(name = "CalendarSelectOneDate", urlPatterns = { "/calendarSelectOneDate" })
public class CalendarSelectOneDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarSelectOneDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		System.out.println(taskNo);
		//비즈니스 로직
		PersonalStudyTask pst = new PersonalStudyService().selectCalendarOntTask(taskNo);
		//결과처리
		if(pst!=null) {
			JSONObject result = new JSONObject();
			result.put("title", URLEncoder.encode(pst.getTaskContent(), "UTF-8"));
			result.put("start", pst.getTaskStartDate());
			result.put("end", pst.getTaskEndDate());
			result.put("color", pst.getTaskColor());
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(result);
			/*버퍼 비우면서  처리*/
			out.flush();
			out.close();
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

