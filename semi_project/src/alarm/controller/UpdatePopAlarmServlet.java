package alarm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alarm.model.service.AlarmService;

/**
 * Servlet implementation class UpdatePopAlarmServlet
 */
@WebServlet(name = "UpdatePopAlarm", urlPatterns = { "/updatePopAlarm" })
public class UpdatePopAlarmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePopAlarmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		int alvalues = Integer.parseInt(request.getParameter("alvalues"));
		//만약에 subject를 3이나 4로 바꿔야되는데 어떨때냐 참여요청이면 3 결과면 4 
		// 알람번호로 알람 서브젝트 구해야함 -> 
		int subject = new AlarmService().getSubject(alvalues);
		System.out.println(subject);
		if(subject == 0) {
			System.out.println("서브젝트 0 ");
		}
		else if(subject == 1) {
			int sub = 3;
			result = new AlarmService().updatePopAlarm(alvalues,sub);
		}
		else {
			int sub = 4;
			result = new AlarmService().updatePopAlarm(alvalues,sub);
		}
		
		
		PrintWriter out = response.getWriter();
		out.print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
