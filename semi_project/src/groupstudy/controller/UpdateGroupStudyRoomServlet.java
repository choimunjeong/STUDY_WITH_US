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
 * Servlet implementation class UpdateGroupStudyRoomServlet
 */
@WebServlet(name = "UpdateGroupStudyRoom", urlPatterns = { "/updateGroupStudyRoom" })
public class UpdateGroupStudyRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGroupStudyRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//첨부파일확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "파일업로드실패 enctype");
			request.setAttribute("loc", "/");
			rd.forward(request, response);
			return;
		}
		
		String saveDirectory = getServletContext().getRealPath("/")+"/upload/groupImg";
		int maxSize = 10*1024*1024;
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		int memberNo = Integer.parseInt(mRequest.getParameter("memberNo"));//다음페이지 이동 시 전달할값
		String category1 = mRequest.getParameter("category1");//다음페이지 이동 시 전달할값
		String category2 = mRequest.getParameter("category2");//다음페이지 이동 시 전달할값
		
		GroupStudyRoom gsr = new GroupStudyRoom();
		gsr.setGroupNo(Integer.parseInt(mRequest.getParameter("groupNo")));
		gsr.setGroupContent(mRequest.getParameter("groupContent"));
		gsr.setGroupEndDate(mRequest.getParameter("groupEndDate"));
		gsr.setGroupExplan(mRequest.getParameter("groupExplan"));
		gsr.setGroupPersonnel(Integer.parseInt(mRequest.getParameter("groupPersonnel")));
		gsr.setGroupRule(mRequest.getParameter("groupRule"));
		gsr.setGroupTitle(mRequest.getParameter("groupTitle"));
		gsr.setFilename(mRequest.getOriginalFileName("filename"));	//첨부파일이 없으면 null저장
		gsr.setFilepath(mRequest.getFilesystemName("filename"));	//첨부파일이 없으면 null저장
		
		//추가항목
		//기존 파일 이름 및 경로
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");
		//파일 삭제 확인용
		String status = mRequest.getParameter("status");
		//현재첨부파일 확인
		File f = mRequest.getFile("filename");
		if(f!=null && f.length()>0) {//새로운 첨부파일이 있는 경우
			if(status.equals("delete")) {//기존첨부파일 삭제한 경우
				File delFile = new File(saveDirectory+"/"+oldFilepath);
				boolean bool = delFile.delete();
				System.out.println(bool?"삭제성공":"삭제실패");
			}
		}else {	//새로운 첨부파일이 없는 경우
			if(status.equals("delete")) {//기존첨부파일을 삭제한 경우
				File delFile = new File(saveDirectory+"/"+oldFilepath);
				boolean bool = delFile.delete();
				System.out.println(bool?"삭제성공":"삭제실패");
			}else if(status.equals("stay")){//원래첨부파일이 없었고 수정할때도 안넣은경우
				gsr.setFilename(oldFilename);
				gsr.setFilepath(oldFilepath);
			}
		}
		
		int result = new GroupStudyService().updateGroupStudyRoom(gsr);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(result>0) {
			request.setAttribute("msg", "수정이 완료되었습니다");
			request.setAttribute("loc", "/myPlanGroupDetail?groupNo="+gsr.getGroupNo()+"&category1="+category1+"&category2="+category2);
		}else {
			request.setAttribute("msg", "수정에 실패했습니다");
			request.setAttribute("loc", "/myGroupStudyList?memberNo="+memberNo);
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
