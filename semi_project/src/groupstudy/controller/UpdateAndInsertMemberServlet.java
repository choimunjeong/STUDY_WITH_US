package groupstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;

/**
 * Servlet implementation class UpdateAndInsertMemberServlet
 */
@WebServlet(name = "UpdateAndInsertMember", urlPatterns = { "/updateAndInsertMember" })
public class UpdateAndInsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAndInsertMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int groupNo = Integer.parseInt(request.getParameter("groupNo")); // 그룹넘
		int memberNo = Integer.parseInt(request.getParameter("applyMemberNo")); //승인받은 멤버
		// 그룹 참여 수락 후, status 변경 및 groupstudymember에 추가
		int result = new GroupStudyService().updateAndInsert(groupNo,memberNo);
		
		PrintWriter out = response.getWriter();
		
		if(result>0) {
			out.print(true);
		}
		else {
			out.print(false);
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
