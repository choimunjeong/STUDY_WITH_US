package personalstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;

import personalstudy.model.service.PersonalStudyService;
import personalstudy.model.vo.PersonalStudyTask;

/**
 * Servlet implementation class MyStudyCalenderServlet
 */
@WebServlet(name = "MyStudyCalender", urlPatterns = { "/myStudyCalender" })
public class MyStudyCalenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyStudyCalenderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		if(memberNo==0) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "로그인 후 이용가능한 페이지 입니다");
			request.setAttribute("loc", "/views/login.jsp");//로그인화면으로 이동
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/myplan/myCalender.jsp");
			ArrayList<PersonalStudyTask> list = new PersonalStudyService().selectCalendarTask(memberNo);
			//json으로 만들기
			JSONArray calendarData = new JSONArray();
			if(list!=null) {
			for(PersonalStudyTask pst : list) {
				JSONObject result = new JSONObject();
				result.put("id",toString().valueOf(pst.getTaskNo()));
				result.put("title", pst.getTaskContent());
				result.put("start", pst.getTaskStartDate());
				result.put("end", pst.getTaskEndDate());
				result.put("color", pst.getTaskColor());
				calendarData.add(result);
			}
			request.setAttribute("calendarData",calendarData);
			rd.forward(request, response);
			}else {
				rd.forward(request, response);
			}
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