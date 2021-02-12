package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alarm.model.service.AlarmService;
import alarm.model.vo.Alarm;
import groupstudy.model.vo.GroupList;
import member.model.service.MemberService;
import member.model.vo.AlarmPageData;
import member.model.vo.GroupPageData;
import member.model.vo.Member;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet(name = "MyPage", urlPatterns = { "/myPage" })
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int alPage = Integer.parseInt(request.getParameter("alPage"));
		int glPage = Integer.parseInt(request.getParameter("glPage"));
		
		Member m = new MemberService().selectOneMember(memberNo);
		ArrayList<GroupList> gl = null;
		
		gl = new MemberService().searchMyStudy(memberNo);
	
		ArrayList<Alarm> al = null;
		al = new AlarmService().searchMyAlarm(memberNo);
		AlarmPageData apd = new MemberService().selectAlarmList(alPage,memberNo,glPage);
		GroupPageData gpd = new MemberService().selectGroupList(alPage,memberNo,glPage);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp");
			request.setAttribute("glNavi", gpd.getPageNavi());
			request.setAttribute("myInfo", m);
			request.setAttribute("group_list", gpd.getList());
			request.setAttribute("myAlarm", apd.getList());
			request.setAttribute("alNavi", apd.getPageNavi());
			
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
