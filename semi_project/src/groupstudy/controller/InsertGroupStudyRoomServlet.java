package groupstudy.controller;

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
 * Servlet implementation class InsertGroupStudyRoomServlet
 */
@WebServlet(name = "InsertGroupStudyRoom", urlPatterns = { "/insertGroupStudyRoom" })
public class InsertGroupStudyRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertGroupStudyRoomServlet() {
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
		
		//categoryNo먼저 받아오기
		String category1 = mRequest.getParameter("category1");
		String category2 = mRequest.getParameter("category2");
		
		int categoryNo = 0;
		
		categoryNo = new GroupStudyService().selectCategoryNo(category1, category2);
		
		if(categoryNo>0) {//가져온 categoryNo을 포함해서 스터디그룹방 생성(insert)
			GroupStudyRoom gsr = new GroupStudyRoom();
			gsr.setCategoryNo(categoryNo);
			gsr.setGroupContent(mRequest.getParameter("groupContent"));
			gsr.setGroupEndDate(mRequest.getParameter("groupEndDate"));
			gsr.setGroupExplan(mRequest.getParameter("groupExplan"));
			gsr.setGroupManagerNo(Integer.parseInt(mRequest.getParameter("groupManagerNo")));
			gsr.setGroupPersonnel(Integer.parseInt(mRequest.getParameter("groupPersonnel")));
			gsr.setGroupRule(mRequest.getParameter("groupRule"));
			gsr.setGroupStartDate(mRequest.getParameter("groupStartDate"));
			gsr.setGroupTitle(mRequest.getParameter("groupTitle"));
			gsr.setFilename(mRequest.getOriginalFileName("filename"));
			gsr.setFilepath(mRequest.getFilesystemName("filename"));
			
			//db에 저장
			int result = new GroupStudyService().insertGroupStudyRoom(gsr);//하나의 conn으로 처리해야함 insert2개가 중간에 잘못될경우 rollback
			
			if(result>0) {	//insert성공
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("msg", "그룹스터디 생성을 완료했습니다");
				request.setAttribute("loc", "/groupStudyList?reqPage=1");//스터디찾기 메인으로
				rd.forward(request, response);
			}else {			//insert실패
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
				request.setAttribute("msg", "그룹스터디 생성에 실패했습니다.[insert error]");
				request.setAttribute("loc", "/groupStudyList?reqPage=1");//스터디찾기 메인으로?
				rd.forward(request, response);
			}
		}else {//categoryNo이 없을경우 에러
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "그룹스터디 생성에 실패했습니다.[not found categoryNo]");
			request.setAttribute("loc", "/groupStudyList?reqPage=1");//스터디찾기 메인으로?
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