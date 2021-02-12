package groupstudy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import groupstudy.model.service.GroupStudyService;
import groupstudy.model.vo.GroupStudyRoom;
import groupstudy.model.vo.GroupStudyRoomAddCategory;
import member.model.vo.Member;

/**
 * Servlet implementation class MyGroupStudyListServlet
 */
@WebServlet(name = "MyGroupStudyList", urlPatterns = { "/myGroupStudyList" })
public class MyGroupStudyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyGroupStudyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		if(memberNo==0) {//로그인이 안되어있으면 0을 보내기 때문에 체크 후 화면 전환
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "로그인 후 이용가능한 페이지 입니다");
			request.setAttribute("loc", "/views/login.jsp");//로그인화면으로 이동
			rd.forward(request, response);
		}else {//else없이해도 브라우저에서만 위에까지동작하는거구 이후에 서블릿은 계속 돌리고있음
			//참여중인 스터디 그룹번호들 가져오기
			ArrayList<Integer> groupNoList = new GroupStudyService().selectGroupNo(memberNo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/myplan/groupList.jsp");
			if(groupNoList.isEmpty()) {//참여중인 스터디가 없을경우 그냥 null로 보냄
				/*ArrayList<GroupStudyRoom> gsrList = new ArrayList<GroupStudyRoom>();
				request.setAttribute("gsrList", gsrList);*/
				rd.forward(request, response);
				return;
			}
			//위에서 가져온 그룹번호로 그룹스터디룸 정보와 카테고리 가져오기(vo새로 만들어서 한쌍으로 가져옴)
			ArrayList<GroupStudyRoomAddCategory> gsrCateList = new GroupStudyService().selectGroupStudyOne(groupNoList);
			
			request.setAttribute("gsrCateList", gsrCateList);
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
