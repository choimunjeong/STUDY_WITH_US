package personalstudy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personalstudy.model.service.PersonalStudyService;
import personalstudy.model.vo.PersonalStudyRoom;
import personalstudy.model.vo.PersonalStudyTask;

/**
 * Servlet implementation class TodayPlanServlet
 */
@WebServlet(name = "TodayPlan", urlPatterns = { "/todayPlan" })
public class TodayPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodayPlanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		if(memberNo==0) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "로그인 후 이용가능한 페이지 입니다");
			request.setAttribute("loc", "/views/login.jsp");//로그인화면으로 이동
			rd.forward(request, response);
		}
		else {
			PersonalStudyRoom perRoom = new PersonalStudyService().selectTimer(memberNo);
			ArrayList<PersonalStudyTask> pst = new PersonalStudyService().selectPersonalTask(memberNo);
			ArrayList<String> gpstl = new PersonalStudyService().selectGroupTask(memberNo);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/myplan/todayPlan.jsp");
			request.setAttribute("pstl", pst);
			request.setAttribute("NTime", perRoom);
			request.setAttribute("gpstl", gpstl);
			rd.forward(request, response);
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
