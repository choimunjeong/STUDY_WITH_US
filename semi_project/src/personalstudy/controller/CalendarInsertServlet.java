package personalstudy.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personalstudy.model.service.PersonalStudyService;
import personalstudy.model.vo.PersonalStudyTask;

/**
 * Servlet implementation class CalendarInsertServlet
 */
@WebServlet(name = "CalendarInsert", urlPatterns = { "/calendarInsert" })
public class CalendarInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//전달받은 값 저장
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String startDate = request.getParameter("startDate");
		//시작날짜 형식을 바꿔줌
		String[] arr = startDate.split(" ");
		if(arr[1].equals("Dec")) {
			arr[1] = "12";
		}
		startDate = arr[3]+"-"+arr[1]+"-"+arr[2];
		System.out.println(startDate);
		String endDate = request.getParameter("endDate");
		String content = request.getParameter("content");
		String color = request.getParameter("color");
		PersonalStudyTask pst = new PersonalStudyTask();
		pst.setMemberNo(memberNo);
		pst.setTaskColor(color);
		pst.setTaskEndDate(endDate);
		pst.setTaskStartDate(startDate);
		pst.setTaskContent(content);	
		//비즈니스 로직
		int result = new PersonalStudyService().insertCalendar(pst);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "스케줄에 추가되었습니다.");
		}else {
			request.setAttribute("msg", "실패");
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