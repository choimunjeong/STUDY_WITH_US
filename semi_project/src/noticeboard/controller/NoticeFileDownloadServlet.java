package noticeboard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDownloadServlet
 */
@WebServlet(name = "NoticeFileDownload", urlPatterns = { "/noticeFileDownload" })
public class NoticeFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		//2.view에서 넘어온 값 저장
		String filename = request.getParameter("filename");
		String filepath = request.getParameter("filepath");
		System.out.println(filename+"그리고"+filepath);
		//3.파일저장경로
		String root = getServletContext().getRealPath("/");
		String saveDirectory =root+"upload/notice/";
		System.out.println("파일 경로 : " + saveDirectory+filepath);
		//파일과 현재 서블릿 연결
		//파일이 서블릿으로 들어왔다가(in) 사용자한테 보내줄 거임(out)
		FileInputStream fis = new FileInputStream(saveDirectory+filepath);
		//속소 개선을 위한 보조스트림
		BufferedInputStream bis = new BufferedInputStream(fis);
		//파일 내보낼 준비:읽어온 파일을 사용자에게 전달하기위한 객체 생성
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		//사용자가 사용하는 브라우져가 IE인지 판단
		String resFilename = "";
		//헤더라는 곳에 어떤 브라우져에 접속했는지 나오는디 user-agent가 있는지 얘가 찾아줄거임
		//요청 정보에는 여러가지 정보가 있는디, 그중 urser-agent가 담당한 것은 어떤 프로그램을 통해 들어왔는지 알 수 있다. msie또는 trident로 들어오면 익스플로져고 아님 나머지이다.
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1 ||
						request.getHeader("user-agent").indexOf("Trident") != -1;
		System.out.println("IE 여부 : " + bool);
		if(bool) {  //사용자의 브라우저가 IE인 경우
 			resFilename = URLEncoder.encode(filename, "UTF-8");
 			resFilename = resFilename.replaceAll("\\\\", "%20");
		}else {  //그 외 브라우져인 경우
			resFilename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
		}
		//파일 다운로드를 위한 HTTP Header 설정
		response.setContentType("application/octet-stream");//파일을 받기만 할거야. 이동은 안할거야
		response.setHeader("Content-Disposition", "attachment;filename="+resFilename);//파일로드되는 파일 이름 설정
		//파일 전송
		int read = -1;
		//bis.read()를 통해 데이터를 읽어서  데이터가 있으면 
		while((read=bis.read()) != -1) {
			bos.write(read);
		}
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
