package personalstudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personalstudy.model.service.PersonalStudyService;
import personalstudy.model.vo.PersonalStudyTask;

/**
 * Servlet implementation class CalendarUpdateServlet
 */
@WebServlet(name = "CalendarUpdate", urlPatterns = { "/calendarUpdate" })
public class CalendarUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//전달받은 값
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		System.out.println("멤버넘버"+memberNo);
		int taskNo = Integer.parseInt(request.getParameter("taskNo"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String content = request.getParameter("content");
		if(content.contains("+")) {
			content = content.replaceAll("\\+", " ");
		}
		String color = request.getParameter("color");
		PersonalStudyTask pst = new PersonalStudyTask();
		pst.setMemberNo(memberNo);
		pst.setTaskColor(color);
		pst.setTaskEndDate(endDate);
		pst.setTaskStartDate(startDate);
		pst.setTaskContent(content);	
		pst.setTaskNo(taskNo);
		//비즈니스 로직
		int result = new PersonalStudyService().updateCalendar(pst);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정되었습니다.");
		}else {
			request.setAttribute("msg", "수정실패");
		}
		request.setAttribute("loc", "/myStudyCalender?memberNo="+memberNo);//로그인화면으로 이동
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}