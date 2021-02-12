package groupstudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alarm.model.service.AlarmService;
import groupstudy.model.service.GroupStudyService;

/**
 * Servlet implementation class InsertApplyGroupMemberServlet
 */
@WebServlet(name = "InsertApplyGroupMember", urlPatterns = { "/insertApplyGroupMember" })
public class InsertApplyGroupMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertApplyGroupMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		//group_apply테이블에 입력하기
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		String applyContent = request.getParameter("applyContent");
		
		int result = new GroupStudyService().insertApplyGroupMember(memberNo,groupNo,applyContent);
		int alResult = new AlarmService().insertApplyAlarm(memberNo,groupNo,applyContent);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		
		if(result>0) {
			if(alResult>0) {
				request.setAttribute("msg", "참여요청이 완료되었습니다");
				request.setAttribute("loc", "/groupStudyDetail?groupNo="+groupNo);
			}
			else {
				request.setAttribute("msg", "알람 Insert에 실패하였습니다");
				request.setAttribute("loc", "/groupStudyDetail?groupNo="+groupNo);
			}
		}else {
			request.setAttribute("msg", "참여요청에 실패하였습니다");
			request.setAttribute("loc", "/groupStudyDetail?groupNo="+groupNo);
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