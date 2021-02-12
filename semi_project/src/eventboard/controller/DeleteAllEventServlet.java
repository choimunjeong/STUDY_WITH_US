package eventboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eventboard.model.service.EventBoardService;
import noticeboard.model.service.NoticeBoardService;

/**
 * Servlet implementation class DeleteAllEventServlet
 */
@WebServlet(name = "DeleteAllEvent", urlPatterns = { "/deleteAllEvent" })
public class DeleteAllEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAllEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 받아온 값 저장
		String num = request.getParameter("num");
		//3. 비즈니스
		ArrayList<String> result = new EventBoardService().deleteAllEvent(num);
		//4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result!= null) {
			String root = getServletContext().getRealPath("/"); //경로를 먼저 만들기
			String saveDirectory = root+"upload/event/";
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
			request.setAttribute("loc", "/eventManagerList?reqPage=1");
		}else {
			request.setAttribute("msg", "이벤트 삭제 대실패");
			request.setAttribute("loc", "/eventManagerList?reqPage=1");
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
