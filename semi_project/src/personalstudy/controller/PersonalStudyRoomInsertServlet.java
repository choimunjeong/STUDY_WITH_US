package personalstudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personalstudy.model.service.PersonalStudyService;
import personalstudy.model.vo.PersonalStudyRoom;

/**
 * Servlet implementation class PersonalStudyRoomInsertServlet
 */
@WebServlet(name = "PersonalStudyRoomInsert", urlPatterns = { "/personalStudyRoomInsert" })
public class PersonalStudyRoomInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalStudyRoomInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 데이터 저장
		System.out.println("서블릿");
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String time = request.getParameter("time");
		//3.비지니스 로직
		int result = new PersonalStudyService().insertPersonalStudyRoom(memberNo, time);
		//4. 결과
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			System.out.println(1);
			request.setAttribute("msg", "등록되었습니다.");
			request.setAttribute("loc", "/todayPlan?memberNo="+memberNo);//일일 계획화면으로 이동			
		}else {
			System.out.println(0);
			request.setAttribute("msg", "등록실패.");
			request.setAttribute("loc", "/todayPlan?memberNo="+memberNo);//일일 계획화면으로 이동
		}
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