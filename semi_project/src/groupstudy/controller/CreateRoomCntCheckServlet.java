package groupstudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;

/**
 * Servlet implementation class CreateRoomCntCheckServlet
 */
@WebServlet(name = "CreateRoomCntCheck", urlPatterns = { "/createRoomCntCheck" })
public class CreateRoomCntCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoomCntCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//스터디찾기 페이지에서 memberNo, memberGrade을 넘겨받음 -> 이걸로 방을 더 생성할 수있는지 체크
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int memberGrade = Integer.parseInt(request.getParameter("memberGrade"));
		System.out.println(memberGrade);
		int roomCnt = new GroupStudyService().createRoomCntCheck(memberNo);
		
		//등급별 방개수제한
		if(memberGrade==1) {//5개
			if(roomCnt>=5) {//이미 최대방개수를 가지고있음
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("msg", "스터디그룹은 최대 5개까지 생성가능합니다");
				request.setAttribute("loc", "/groupStudyList?reqPage=1");//스터디 찾기 페이지 경로로 변경 필요
				rd.forward(request, response);
			}else {//방생성가능
				RequestDispatcher rd = request.getRequestDispatcher("/views/createGroupStudy.jsp");
				rd.forward(request, response);
			}
		}else {//1개
			if(roomCnt>=1) {//이미 최대방개수를 가지고있음
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("msg", "현재 등급에서 스터디그룹은 최대 1개까지 생성가능합니다");
				request.setAttribute("loc", "/groupStudyList?reqPage=1");//스터디 찾기 페이지 경로로 변경 필요
				rd.forward(request, response);
			}else {//방생성가능
				RequestDispatcher rd = request.getRequestDispatcher("/views/createGroupStudy.jsp");
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
