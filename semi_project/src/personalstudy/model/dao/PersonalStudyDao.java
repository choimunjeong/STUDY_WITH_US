package personalstudy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import personalstudy.model.vo.PersonalStudyRoom;
import personalstudy.model.vo.PersonalStudyTask;

public class PersonalStudyDao {

	public ArrayList<PersonalStudyTask> selectPersonalTask(Connection conn, int memberNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from personal_studytask WHERE to_char(sysdate,'yyyy-mm-dd') BETWEEN TASK_STARTDATE AND task_enddate and member_no = ?";
		ArrayList<PersonalStudyTask> pstl = new ArrayList<PersonalStudyTask>();
		PersonalStudyTask pst = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
		//	pstmt.setInt(2, 1);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				pst = new PersonalStudyTask();
				pst.setMemberNo(rset.getInt("member_no"));
				pst.setTaskColor(rset.getString("task_color"));
				pst.setTaskContent(rset.getString("task_content"));
				pst.setTaskEndDate(rset.getString("task_enddate"));
				pst.setTaskNo(rset.getInt("task_no"));
				pst.setTaskOrder(rset.getInt("task_order"));
				pst.setTaskStartDate(rset.getString("task_startdate"));
				pst.setTaskStatus(rset.getString("task_status"));
				pstl.add(pst);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return pstl;
	}


	public int insertList(Connection conn, String color, String listTitle, int memberNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into personal_studytask values(personal_studytask_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),to_char(sysdate,'yyyy-mm-dd'),?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, listTitle);
			pstmt.setString(3, color);
			pstmt.setString(4, "x");
			pstmt.setInt(5,1);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	public ArrayList<String> selectGroupTask(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select group_explan from group_studyroom g1 join group_studymember g2 using(group_no) where g2.member_no=?";
		ArrayList<String> list = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(rset.getString("group_explan"));
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


	public int deleteTask(Connection conn, int index, int memberNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from personal_studytask where rowid = (select rid from (select rownum rn, rowid rid from personal_studytask where member_no= ?) where rn= ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, index);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	public int insertGroupGoal(Connection conn, int memberNo, String content) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into personal_studytask values(PERSONAL_STUDYTASK_SEQ.NEXTVAL,?,?,to_char(sysdate,'yyyy-mm-dd'),to_char(sysdate,'yyyy-mm-dd'),?,'x',?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2,content);
			pstmt.setString(3,"black");
			pstmt.setInt(4,2);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//일일계획-시간저장	12/02 16:08
		public int insertPersonalStudyRoom(Connection conn, int memberNo, String time) {
			System.out.println("DAO");
			System.out.println(memberNo);
			System.out.println(time);
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "insert into personal_studyRoom values(?,to_char(sysdate,'yyyy-mm-dd'),'goal',to_char(sysdate, 'HH24:MI:SS'),?)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				pstmt.setString(2, time);
				result = pstmt.executeUpdate();
				System.out.println(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		//개인 스터디(달력:스케줄) - 데이터 불러오기
		public ArrayList<PersonalStudyTask> selectCalendarTask(Connection conn, int memberNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<PersonalStudyTask> list = new ArrayList<PersonalStudyTask>();
			String query = "select task_no, member_no, task_content, task_startdate, task_enddate, task_color from PERSONAL_STUDYTASK where member_no = ? and task_startdate is not null";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					PersonalStudyTask pst = new PersonalStudyTask();
					pst.setMemberNo(rset.getInt("MEMBER_NO"));
					pst.setTaskColor(rset.getString("TASK_COLOR"));
					pst.setTaskContent(rset.getString("TASK_CONTENT"));
					pst.setTaskEndDate(rset.getString("TASK_ENDDATE"));
					pst.setTaskStartDate(rset.getString("TASK_STARTDATE"));
					pst.setTaskNo(rset.getInt("TASK_NO"));
					list.add(pst);
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

		//개인 스터디(달력:스케줄) - 데이터 삽입
		public int insertCalendar(Connection conn, PersonalStudyTask pst) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "insert into PERSONAL_STUDYTASK values (PERSONAL_STUDYTASK_SEQ.NEXTVAL, ?,?,?,?,?,'n',0)";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, pst.getMemberNo());
				pstmt.setString(2, pst.getTaskContent());
				pstmt.setString(3, pst.getTaskStartDate());
				pstmt.setString(4, pst.getTaskEndDate());
				pstmt.setString(5, pst.getTaskColor());
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		//개인 스터디(달력:스케줄) - 1개의 할 일 내용 받기
		public PersonalStudyTask selectCalendarOneTask(Connection conn, int taskNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String qeury = "select task_content, task_startdate, task_enddate, task_color from PERSONAL_STUDYTASK where task_no = ? ";
			PersonalStudyTask pst = null;
			try {
				pstmt = conn.prepareStatement(qeury);
				pstmt.setInt(1, taskNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					pst = new PersonalStudyTask();
					pst.setTaskColor(rset.getString("TASK_COLOR"));
					pst.setTaskContent(rset.getString("TASK_CONTENT"));
					pst.setTaskEndDate(rset.getString("TASK_ENDDATE"));
					pst.setTaskStartDate(rset.getString("TASK_STARTDATE"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
				JDBCTemplate.close(rset);
			}
			return pst;
		}

		//개인 스터디(달력:스케줄) - 할 일 삭제
		public int deleteCalendar(Connection conn, int taskNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "delete from PERSONAL_STUDYTASK where task_NO = ?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, taskNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}

		//개인 스터디(달력:스케줄) - 기존 할 일 수정
		public int updateCalendar(Connection conn, PersonalStudyTask pst) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = "update PERSONAL_STUDYTASK set  TASK_CONTENT=?, TASK_STARTDATE=?, TASK_ENDDATE=?,TASK_COLOR=? where task_no=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, pst.getTaskContent());
				pstmt.setString(2, pst.getTaskStartDate());
				pstmt.setString(3, pst.getTaskEndDate());
				pstmt.setString(4, pst.getTaskColor());
				pstmt.setInt(5, pst.getTaskNo());
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}
			return result;
		}


		public PersonalStudyRoom selectTimer(Connection conn, int memberNo) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			PersonalStudyRoom perRoom = null;
			String query = "select study_totaltime from personal_studyroom where member_no = ? and today_date = to_char(sysdate,'yyyy-mm-dd')";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, memberNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					perRoom = new PersonalStudyRoom();
					perRoom.setMemberNo(memberNo);
					perRoom.setStudyTotalTime(rset.getString("study_totaltime"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return perRoom;
		}
}
