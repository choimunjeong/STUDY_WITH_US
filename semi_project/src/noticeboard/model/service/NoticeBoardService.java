package noticeboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.JDBCTemplate;
import noticeboard.model.dao.NoticeBoardDao;
import noticeboard.model.vo.NoticeBoard;
import noticeboard.model.vo.NoticeBoardPage;

public class NoticeBoardService {
	public NoticeBoardPage seleteList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		//1. 전체 글 수 검색
		int totalCount = new NoticeBoardDao().totalCount(conn);
		
		//2. 총 페이지 수
		int numPerPage = 10;
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		
		//3.게시글 시작번호, 끝번호
		int start = (reqPage-1)*numPerPage + 1;
		int end = reqPage*numPerPage;
		
		//4. 페이지 범위에 있는 게시글 리스트를 불러옴
		ArrayList<NoticeBoard> list = new NoticeBoardDao().selectList(conn, start, end);
		
		//5.페이지 네비게이션 생성
		int pageNaviSize = 5;
		String pageNavi = "";
		
		//6.페이지 번호
		int pageNo = reqPage-2;
		if(reqPage <= 3) {
			pageNo = 1;
		}else if(pageNo > totalPage-4) {
			pageNo = totalPage -4;
		}
		
		//7. 이전버튼
		if(reqPage > 3) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/noticeMangerList?reqPage="+(pageNo-1)+"'><<</a></li>";
		}
		
		//8. 네이게이션 숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(reqPage == pageNo) {
				pageNavi += "<li class='page-item'><a class='page-link' href='#' style='background-color:#6ED078'>"+pageNo+"</a></li>";
			}else {
				pageNavi += "<li class='page-item'><a class='page-link' href='/noticeMangerList?reqPage="+(pageNo)+"'>"+pageNo+"</a></li>";
			}
			pageNo++;
			
			if(pageNo > totalPage) {
				break;
			}
		}

		//9. 다음버튼
		if(reqPage <= totalPage-3) {
			pageNavi += "<li class='page-item'><a class='page-link' href='/noticeMangerList?reqPage="+pageNo+"'>>></a></li>";
		}
		
		if(pageNo==2) {
			pageNavi="";
		}
		
		//10. 리스트+태그텍스트+멤버 카운트를 객체에 넣어줌
		NoticeBoardPage nbp = new NoticeBoardPage(list, pageNavi);
		JDBCTemplate.close(conn);
		return nbp;
	}

	public NoticeBoard selectNoticeView(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeBoard n = new NoticeBoardDao().selectOneNotice(conn, noticeNo);
		JDBCTemplate.close(conn);
		return n;
	}

	public int updateNotice(NoticeBoard n) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeBoardDao().updateNotice(conn, n);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertNotice(NoticeBoard n) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeBoardDao().insertNotice(conn, n);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public boolean deleteAllNotice(String num) {
		Connection conn = JDBCTemplate.getConnection();
		System.out.println("num : " + num);
		StringTokenizer sT1 = new StringTokenizer(num, "/");
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int noticeNo = Integer.parseInt(sT1.nextToken());
			int result1 = new NoticeBoardDao().deleteNotice(conn, noticeNo);
			if(result1==0) {
				result = false;
				break;
			}
			if(result) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteOneNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeBoardDao().deleteNotice(conn, noticeNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}