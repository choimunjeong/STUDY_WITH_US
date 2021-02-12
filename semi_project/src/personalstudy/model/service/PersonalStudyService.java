package personalstudy.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import personalstudy.model.dao.PersonalStudyDao;
import personalstudy.model.vo.PersonalStudyRoom;
import personalstudy.model.vo.PersonalStudyTask;

public class PersonalStudyService {

	public ArrayList<PersonalStudyTask> selectPersonalTask(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<PersonalStudyTask> pst = new PersonalStudyDao().selectPersonalTask(conn,memberNo);
		JDBCTemplate.close(conn);
		return pst;
	}

	public int insertList(String color, String listTitle, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PersonalStudyDao().insertList(conn,color,listTitle,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<String> selectGroupTask(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<String> list = new ArrayList<String>();
		list = new PersonalStudyDao().selectGroupTask(conn,memberNo);
		ArrayList<String> newList = new ArrayList<String>();
		
		for(String str : list) {
			String[] array = str.split("_");
			for(int i=0;i<array.length;i++)
			{
					if(newList.size()>8) {
						break;
					}
					newList.add(array[i]);
			}
			
		}
//			for(int i=0;i<array.length;i++)
//			{
//				System.out.println(array[i]);
//				newList.add(array[i]);
//			}
			
		return newList;
	}

	public int deleteTask(int index, int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PersonalStudyDao().deleteTask(conn,index,memberNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertGroupGoal(int memberNo, String content) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new PersonalStudyDao().insertGroupGoal(conn,memberNo,content);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	//일일계획-시간저장	12/02 16:08
		public int insertPersonalStudyRoom(int memberNo, String time) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new PersonalStudyDao().insertPersonalStudyRoom(conn, memberNo, time);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			System.out.println(result);
			return result;
		}


		//개인 스터디(달력:스케줄) - 데이터 불러오기
		public ArrayList<PersonalStudyTask> selectCalendarTask(int memberNo) {
			Connection conn = JDBCTemplate.getConnection();
			ArrayList<PersonalStudyTask> list = new PersonalStudyDao().selectCalendarTask(conn, memberNo);
			JDBCTemplate.close(conn);
			return list;
		}

		//개인 스터디(달력:스케줄) - 데이터 삽입
		public int insertCalendar(PersonalStudyTask pst) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new PersonalStudyDao().insertCalendar(conn, pst);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

		//개인 스터디(달력:스케줄) - 1개의 할 일 내용 받기
		public PersonalStudyTask selectCalendarOntTask(int taskNo) {
			Connection conn = JDBCTemplate.getConnection();
			PersonalStudyTask pst = new PersonalStudyDao().selectCalendarOneTask(conn, taskNo);
			JDBCTemplate.close(conn);
			return pst;
		}
		
		//개인 스터디(달력:스케줄) - 할 일 삭제
		public int deleteCalendar(int taskNo) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new PersonalStudyDao().deleteCalendar(conn, taskNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

		//개인 스터디(달력:스케줄) - 기존 할 일 수정
		public int updateCalendar(PersonalStudyTask pst) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new PersonalStudyDao().updateCalendar(conn, pst);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

		public PersonalStudyRoom selectTimer(int memberNo) {
			Connection conn = JDBCTemplate.getConnection();
			PersonalStudyRoom perRoom = new PersonalStudyDao().selectTimer(conn,memberNo);
			JDBCTemplate.close(conn);
			return perRoom;
		}

}
