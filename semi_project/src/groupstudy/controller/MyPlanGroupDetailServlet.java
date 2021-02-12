package groupstudy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.dao.GroupStudyDao;
import groupstudy.model.service.GroupStudyService;
import groupstudy.model.vo.GroupComment;
import groupstudy.model.vo.GroupCommentMemberFilePath;
import groupstudy.model.vo.GroupStudyRoom;

/**
 * Servlet implementation class MyPlanGroupDetailServlet
 */
@WebServlet(name = "MyPlanGroupDetail", urlPatterns = { "/myPlanGroupDetail" })
public class MyPlanGroupDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPlanGroupDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		String category1 = request.getParameter("category1");
		String category2 = request.getParameter("category2");
		
		//groupList.jsp에서 선택한 그룹스터디의 상세페이지를 보여주기위한 정보
		GroupStudyRoom gsr = new GroupStudyService().selectGroupStudyOne(groupNo);
		//인원수 가져오기
		int memberCnt = new GroupStudyService().selectMemberNo(groupNo);
		//댓글 및 자료실 첨부파일 가져오기(같은 테이블)
		//댓글가져올때 사용자들의 프로필사진도 가져와야해서 새로운 vo를 만들어서 짝지어서 가져옴
		GroupCommentMemberFilePath gcmf = new GroupStudyService().selectGroupCommentAll(groupNo);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/myplan/myPlanGroupDetail.jsp");
		
		
		request.setAttribute("gsr", gsr);
		request.setAttribute("gcList", gcmf.getGcList());
		request.setAttribute("memberIdFileMap", gcmf.getMemberIdFileMap());
		request.setAttribute("category1", category1);
		request.setAttribute("category2", category2);
		request.setAttribute("memberCnt", memberCnt);
		
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