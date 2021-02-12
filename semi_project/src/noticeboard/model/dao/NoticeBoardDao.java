package noticeboard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import noticeboard.model.vo.NoticeBoard;

public class NoticeBoardDao {

	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalCount = 0;
		String query = "select count(*) totalCount from NOTICE_BOARD";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt("totalCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		
		return totalCount;
	}

	public ArrayList<NoticeBoard> selectList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<NoticeBoard> list = new ArrayList<NoticeBoard>();
		String query = "select * from (select rownum as rnum, n.* from(select * from notice_board order by notice_status, NOTICE_ENROLLDATE desc ) n) where rnum between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeBoard n = new NoticeBoard();
				n.setRnum(rset.getInt("rnum"));
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeEnrollDate(rset.getString("NOTICE_ENROLLDATE"));
				n.setNoticeStatus(rset.getInt("NOTICE_STATUS"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return list;
	}

	public NoticeBoard selectOneNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		NoticeBoard n = null;
		String query = "select * from notice_board where notice_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				n = new NoticeBoard();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeEnrollDate(rset.getString("NOTICE_ENROLLDATE"));
				n.setNoticeStatus(rset.getInt("NOTICE_STATUS"));
				n.setFilename(rset.getString("filename"));
				n.setFilepath(rset.getString("filepath"));
				n.setNoticeContent(rset.getString("notice_content"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return n;
	}

	public int updateNotice(Connection conn, NoticeBoard n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice_board set notice_title=?, notice_content=?,filename=?,filepath=? where notice_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2,  n.getNoticeContent());
			pstmt.setString(3, n.getFilename());
			pstmt.setString(4, n.getFilepath());
			pstmt.setInt(5,  n.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertNotice(Connection conn, NoticeBoard n) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice_board values(NOTICE_BOARD_SEQ.NEXTVAL, ?,?, ?, ?, ? ,  to_char(sysdate, 'yyyy-mm-dd'))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getNoticeTitle() );
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setString(3, n.getFilename());
			pstmt.setString(4, n.getFilepath());
			pstmt.setInt(5,  n.getNoticeStatus());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//공지사항 삭제
	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice_board where notice_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
