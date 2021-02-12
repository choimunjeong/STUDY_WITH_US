package groupstudy.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import groupstudy.model.service.GroupStudyService;
import groupstudy.model.vo.GroupStudyRoom;

/**
 * Servlet implementation class DeleteGroupStudyRoomServlet
 */
@WebServlet(name = "DeleteGroupStudyRoom", urlPatterns = { "/deleteGroupStudyRoom" })
public class DeleteGroupStudyRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGroupStudyRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		GroupStudyRoom gsr = new GroupStudyService().selectGroupStudyOne(groupNo);//첨부파일삭제를 위한filepath값구해오기
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));//참여중인 리스트페이지로 돌려보내기위한 값
		
		int result = new GroupStudyService().deleteGroupStudyRoom(groupNo);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			if(gsr.getFilepath()!=null) {//첨부파일이 있을 경우같이 삭제해줘야함
				String saveDirectory = getServletContext().getRealPath("/")+"/upload/groupImg/";
				File delFile = new File(saveDirectory+gsr.getFilepath());
				if(delFile.delete()) {
					System.out.println(gsr.getFilepath()+"첨부파일삭제 성공");
				}else {
					System.out.println(gsr.getFilepath()+"첨부파일삭제 실패");
				}
			}
			request.setAttribute("msg", "삭제에 성공하였습니다");
		}else {
			request.setAttribute("msg", "삭제에 실패하였습니다");
		}
		request.setAttribute("loc", "/myGroupStudyList?memberNo="+memberNo);
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
