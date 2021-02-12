package manager.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupstudy.model.service.GroupStudyService;
import member.model.service.MemberService;

/**
 * Servlet implementation class DeleteAllGroupServlet
 */
@WebServlet(name = "DeleteAllGroup", urlPatterns = { "/deleteAllGroup" })
public class DeleteAllGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAllGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.view에서 보낸 값 저장
		String num = request.getParameter("num");
		//3.비즈니스 로직
		ArrayList<String> result = new GroupStudyService().deleteAllMember(num);
		//4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result != null) {
			String root = getServletContext().getRealPath("/"); //경로를 먼저 만들기
			String saveDirectory = root+"upload/groupImg/";
			File delFile;
			for(int i=0; i<result.size(); i++) {
				delFile = new File(saveDirectory+result.get(i));
				boolean delResult = delFile.delete();
				if(delResult) {
					System.out.println("파일 삭제 성공");
				}else {
					System.out.println("파일 삭제 실패");
				}
			}
			request.setAttribute("msg", "삭제완료");
			request.setAttribute("loc", "/groupStudyListManager?reqPage=1");
		}else {
			request.setAttribute("msg", "그룹 삭제 대실패");
			request.setAttribute("loc", "/groupStudyListManager?reqPage=1");
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
