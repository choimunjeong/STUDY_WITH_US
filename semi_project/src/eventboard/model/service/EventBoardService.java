package eventboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import eventboard.model.dao.EventBoardDao;
import eventboard.model.vo.EventBoard;
import eventboard.model.vo.EventBoardPage;
import noticeboard.model.dao.NoticeBoardDao;
import noticeboard.model.vo.NoticeBoard;

public class EventBoardService {

	public EventBoardPage selectList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		//1. 전체 게시글 검색
		int totalCount = new EventBoardDao().totalCount(conn);
		
		//2. 총 페이지 수
		int numPerPage = 10;
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage +1;
		}
		
		//3. 게시글 시작, 마지막 페이지
		int start = (reqPage-1)*numPerPage + 1;
		int end = reqPage*numPerPage;
		
		//4. 페이지 범위에 있는 게시글 불러옴
		ArrayList<EventBoard> list = new EventBoardDao().selectList(start, end, conn);
		
		//5. 페이지 네비게이션
		int pageNaviSize = 5;
		String pageNavi = "";
		
		//6. 페이지 번호
		int pageNo = reqPage -2;
		if(reqPage < 3) {
			pageNo = 1;
		}else if(pageNo > totalPage-4) {
			pageNo = totalPage - 4;
		}
		
		//7. 이전버튼
		if(reqPage > 3) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/eventManagerList?reqPage="+(pageNo-1)+"'><<</a></li>";
		}
		
		//8. 네이게이션 숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<li class='page-item'><a class='page-link' href='#' style='background-color:#6ED078'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/eventManagerList?reqPage="+(pageNo)+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}

		//9. 다음버튼
		if(reqPage <= totalPage-3) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/eventManagerList?reqPage="+pageNo+"'>>></a></li>";
		}

		if(pageNo==2) {
			pageNavi="";
		}
		
		//10. 리스트+태그텍스트+멤버 카운트를 객체에 넣어줌
		EventBoardPage ebp = new EventBoardPage(list, pageNavi);
		JDBCTemplate.close(conn);
		return ebp;
	}

	public int insertEvent(EventBoard e) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventBoardDao().insertEvent(conn, e);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public EventBoard selectEventView(int eventNo) {
		Connection conn = JDBCTemplate.getConnection();
		EventBoard e = new EventBoardDao().selectOneEvent(conn, eventNo);
		JDBCTemplate.close(conn);
		return e;
	}

	public ArrayList<String> deleteAllEvent(String num) {
		Connection conn = JDBCTemplate.getConnection();
		StringTokenizer st = new StringTokenizer(num, "/");
		ArrayList<String> imgList = new ArrayList<String>();
		while(st.hasMoreTokens()) {
			int eventNo = Integer.parseInt(st.nextToken());
			String filepath = new EventBoardDao().deletefilepath(conn, eventNo);
			if(filepath != "") {
				imgList.add(filepath);
			}
			int result = new EventBoardDao().deleteEvent(conn, eventNo);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
				return null;
			}
		}
		JDBCTemplate.close(conn);
		if(imgList.size() > 0) {
			return imgList;
		}
		return null;
	}

	public int deleteOneEvent(int eventNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new EventBoardDao().deleteEvent(conn, eventNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String deleteFilepath(int eventNo) {
		Connection conn = JDBCTemplate.getConnection();
		String filepath = new EventBoardDao().deletefilepath(conn, eventNo);
		JDBCTemplate.close(conn);
		return filepath;
	}
}