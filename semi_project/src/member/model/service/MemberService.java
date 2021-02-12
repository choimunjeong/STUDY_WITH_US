package member.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import alarm.model.dao.AlarmDao;
import alarm.model.vo.Alarm;
import common.JDBCTemplate;
import groupstudy.model.dao.GroupStudyDao;
import groupstudy.model.vo.GroupList;
import member.model.dao.MemberDao;
import member.model.vo.AlarmPageData;
import member.model.vo.GroupPageData;
import member.model.vo.MailSend;
import member.model.vo.Member;
import member.model.vo.MemberManagePage;

public class MemberService {
	
	// 마이페이지 -> 알림리스트 -> 페이징
	public AlarmPageData selectAlarmList(int alPage, int member_no, int glPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		AlarmDao dao = new AlarmDao();
		
		int totalCnt = dao.totalCount(conn,member_no); // 총 게시물의 수를 DB에서 가져옴.
		
		int numPerPage = 5; // 한 페이지 당 나타낼 게시글의 개수
		
		int totalPage = 0; // 총 나타내야 할 페이지 인덱스의 개수 
		
		if(totalCnt%numPerPage == 0 ) 
		{
			totalPage = totalCnt/numPerPage;
			// 나누어 떨어지면 나눈 값 
		}
		else {
			totalPage = totalCnt/numPerPage+1;
			// 나누어 떨어지지않고 게시글이 남는다면 인덱스 +1 
		}

		int start = (alPage-1)*numPerPage + 1; // 조회할 게시물들의 시작번호 
		int end = alPage*numPerPage; // 조회할 게시물들의 마지막번호
		System.out.println(start+","+end);
		ArrayList<Alarm> list = dao.selectList(conn,member_no,start,end); // 게시글 리스트를 받아옴.
		
		//페이지 네비게이션 작성 시작
		int pageNaviSize = 5; // 보여주는 페이지 인덱스 5개로 지정 
		
		String pageNavi = ""; // 페이지 네비 태그 작성용 (innerHTML)
		
		//페이지네비 시작번호 구하기 
		// reqPage : 1 ~ 5 -> 1 , reqPage : 6 ~ 10 -> 6 
		int pageNo = ((alPage-1)/pageNaviSize)*pageNaviSize+1;
		
		// Prev 버튼 : 페이지 시작번호가 1이 아닌경우에만 생성 
		if(pageNo != 1) {
			pageNavi += "<a class='btn' href='/myPage?memberNo="+member_no+"&glPage="+glPage+"&alPage="+(pageNo-1)+"'>이전</a>";
		}
		// Page Navi 숫자 
		for(int i=0;i<pageNaviSize;i++)
		{
			if(alPage == pageNo) // 페이지 네비가 현재 요청페이지인경우 (a 태그 필요 X)
			{
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}
			else {
				pageNavi += "<a class='btn' href='/myPage?memberNo="+member_no+"&glPage="+glPage+"&alPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
			
		}
		// Next 버튼 
		if(pageNo <= totalPage)
		{
			pageNavi += "<a class='btn' href='/myPage?memberNo="+member_no+"&glPage="+glPage+"&alPage="+pageNo+"'>다음</a>";
		}
		
		AlarmPageData apd = new AlarmPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		
		return apd;
	}
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn,m);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Boolean selectOneMember(String memberId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		Boolean b = new MemberDao().selectOneMember(conn, memberId);
		
		JDBCTemplate.close(conn);
		
		return b;
	}

	
	public Member selectOneMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member loginMember = new MemberDao().selectOneMember(conn, m);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
	}

	public String findMemberId(String searchName, String searchEmail) {
		
		Connection conn = JDBCTemplate.getConnection();
		String s_id = new MemberDao().findMemberId(conn, searchName, searchEmail);
		
		JDBCTemplate.close(conn);
		return s_id;
	}

	public static String randomPassword (int length) {
		int index = 0;
		char[] charSet = new char[] {
				'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H',
				'I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
				'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r',
				's','t','u','v','w','x','y','z'};
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<length;i++) {
			index = (int) (charSet.length * Math.random());
			sb.append(charSet[index]);
		}
		return sb.toString();
	}
	
	public int findMemberPw(String searchId, String searchMail) {

		Connection conn = JDBCTemplate.getConnection();
		String n_pw = randomPassword(10); // 임시 비밀번호 생성
		int result = new MemberDao().findMemberPw(conn, searchId, searchMail,n_pw);
		if(result>0) {
			JDBCTemplate.commit(conn);
			String mailCode = new MailSend().mailSend(searchMail,n_pw);
			System.out.println("서비스 : "+mailCode);
		}
		else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);
		
		return result;
	}

	public Member selectOneMember(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().selectOneMember(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return m;
	}

	public ArrayList<GroupList> searchMyStudy(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<GroupList> gl = new MemberDao().searchMyStudy(conn,memberNo);
		
		JDBCTemplate.close(conn);
		
		return gl;
	}

	public int updateMember(Member updateMember) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn,updateMember);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteMember(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn,memberNo);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public GroupPageData selectGroupList(int alPage, int memberNo, int glPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		GroupStudyDao dao = new GroupStudyDao();
		
		int totalCnt = dao.totalCount(conn,memberNo); // 총 게시물의 수를 DB에서 가져옴.
		
		int numPerPage = 5; // 한 페이지 당 나타낼 게시글의 개수
		
		int totalPage = 0; // 총 나타내야 할 페이지 인덱스의 개수 
		
		if(totalCnt%numPerPage == 0 ) 
		{
			totalPage = totalCnt/numPerPage;
			// 나누어 떨어지면 나눈 값 
		}
		else {
			totalPage = totalCnt/numPerPage+1;
			// 나누어 떨어지지않고 게시글이 남는다면 인덱스 +1 
		}

		int start = (glPage-1)*numPerPage + 1; // 조회할 게시물들의 시작번호 
		int end = glPage*numPerPage; // 조회할 게시물들의 마지막번호
		System.out.println(start+","+end);
		ArrayList<GroupList> list = dao.selectList(conn,memberNo,start,end); // 게시글 리스트를 받아옴.
		
		//페이지 네비게이션 작성 시작
		int pageNaviSize = 5; // 보여주는 페이지 인덱스 5개로 지정 
		
		String pageNavi = ""; // 페이지 네비 태그 작성용 (innerHTML)
		
		//페이지네비 시작번호 구하기 
		// reqPage : 1 ~ 5 -> 1 , reqPage : 6 ~ 10 -> 6 
		int pageNo = ((glPage-1)/pageNaviSize)*pageNaviSize+1;
		
		// Prev 버튼 : 페이지 시작번호가 1이 아닌경우에만 생성 
		if(pageNo != 1) {
			pageNavi += "<a class='btn' href='/myPage?memberNo="+memberNo+"&glPage="+(pageNo-1)+"&alPage="+alPage+"'>이전</a>";
		}
		// Page Navi 숫자 
		for(int i=0;i<pageNaviSize;i++)
		{
			if(glPage == pageNo) // 페이지 네비가 현재 요청페이지인경우 (a 태그 필요 X)
			{
				pageNavi += "<span class='selectPage'>"+pageNo+"</span>";
			}
			else {
				pageNavi += "<a class='btn' href='/myPage?memberNo="+memberNo+"&glPage="+pageNo+"&alPage="+alPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
	}
		// Next 버튼 
		if(pageNo <= totalPage)
		{
			pageNavi += "<a class='btn' href='/myPage?memberNo="+memberNo+"&glPage="+pageNo+"&alPage="+alPage+"'>다음</a>";
		}
		
		GroupPageData gpd = new GroupPageData(list, pageNavi);
		JDBCTemplate.close(conn);
		return gpd;
}
	public ArrayList<Member> searchSendMember(ArrayList<Alarm> al) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> ml = new ArrayList<Member>();
		Member m = null;
		for(int i=0;i<al.size();i++) {
			m = new Member();
			m = new MemberDao().searchSendMember(conn,al.get(i).getSendMemberNo());
			ml.add(m);
		}
		JDBCTemplate.close(conn);
		return ml;
	}
	
	//관리자페이지에서 사용자 삭제
		public boolean deleteAllUser(String num) {
			Connection conn = JDBCTemplate.getConnection();
			StringTokenizer sT1 = new StringTokenizer(num, "/");
			boolean result = true;
			while(sT1.hasMoreTokens()) {
				int memberNo = Integer.parseInt(sT1.nextToken());
				int result1 = new MemberDao().deleteUser(conn, memberNo);
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

		//관리자 페이지 - 사용자 레벨 변경
		public int changeLevel(int memberLevel, int memberNo) {
			Connection conn = JDBCTemplate.getConnection();
			int result = new MemberDao().changeLevel(conn, memberLevel,memberNo);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			return result;
		}

		//관리자 페이지 - 사용자 리스트 페이징 조회
		public MemberManagePage selectList(int reqPage) {
			Connection conn = JDBCTemplate.getConnection();
			
			//1. 전체 글 수를 검색
			int totalCount = new MemberDao().totalCount(conn);
					
			//2. 총 페이지 수
			int numPerPage = 10;
			int totalPage = 0;
			if(totalCount%numPerPage == 0) {
				totalPage = totalCount/numPerPage;
			}else {
				totalPage = totalCount/numPerPage + 1;
			}
			
			//3. 게시글 시작번호화 끝 번호
			int start = (reqPage-1)*numPerPage + 1;
			int end = reqPage*numPerPage;
			
			//4.페이지 범위에 있는 게시글 리스트를 불러옴
			ArrayList<Member> list = new MemberDao().selectList(conn, start, end);
			
			//5.페이지 네비게이션 생성
			int pageNaviSize = 5;
			String pageNavi = "";
			
			//6.페이징 번호
			int pageNo = reqPage-2;
			if(reqPage <=3) {
				pageNo = 1;
			}else if(pageNo > totalPage-4){
				pageNo = totalPage-4;
			}
			
			//7. 이전버튼
			if(reqPage > 3) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/memberList?reqPage="+(pageNo-1)+"'><<</a></li>";
			}
			
			//8. 네비게이션 숫자
			for(int i=0; i<pageNaviSize; i++) {
				if(reqPage==pageNo) {
					pageNavi += "<li class='page-item'><a class='page-link' href='#' style='background-color:#6ED078'>"+pageNo+"</a></li>";
					pageNavi += "<li class='reqPage' style='display:none'>"+reqPage+"</li>";
				}else {
					pageNavi += "<li class='page-item'><a class='page-link' href='/memberList?reqPage="+(pageNo)+"'>"+pageNo+"</a></li>";
				}
				pageNo++;
				
				if(pageNo > totalPage) {
					break;
				}
			}
			
			//9. 다음버튼
			if(pageNo <= totalPage-3) {
				pageNavi += "<li class='page-item'><a class='page-link' href='/memberList?reqPage="+pageNo+"'>>></a></li>";
			}
			
			if(pageNo==2) {
				pageNavi="";
			}
			
			//10. 리스트와 태그 텍스트를 객체에 넣어줌
			MemberManagePage mmp = new MemberManagePage(list, pageNavi);
			JDBCTemplate.close(conn);
			return mmp;
		}
		public int snsLogin(String id, String name, String image) {
			Connection conn = JDBCTemplate.getConnection();
			MemberDao dao = new MemberDao();
			int result = dao.searchSnsMember(conn,id); //0 아니면 1이 나오는데 0 이면 인서트 1이면 그냥 반환 
			if(result == 0) {
				// 0이면 DB에 insert
				int inResult = dao.insertSnsMember(conn,id,name,image);
				if(inResult>0) {
					JDBCTemplate.commit(conn);
					result = 1;
				}
				else {
					JDBCTemplate.rollback(conn);
				}
			}
				JDBCTemplate.close(conn);
			return result;
		}
		public Member selectSnsMember(String id) {
			Connection conn = JDBCTemplate.getConnection();
			Member member = new MemberDao().selectSnsMember(conn,id);
			JDBCTemplate.close(conn);
			return member;
		}
		public HashMap<String, String> getAccessToken(String path) {
			HashMap<String,String> token = new HashMap<String,String>();
			String access_token;
			String refresh_token;
			try {
			URL url = new URL(path);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			Scanner sc = new Scanner(is);
			String str = null;
			if(sc.hasNext()) {
				 str = sc.nextLine();
			}
			JSONParser parsing = new JSONParser();
			Object obj;
			obj = parsing.parse(str);
			JSONObject jsonObj = (JSONObject) obj;
			access_token = (String)jsonObj.get("access_token");
			refresh_token = (String)jsonObj.get("refresh_token");
			token.put("access", access_token);
			token.put("refresh", refresh_token);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return token;
		}
		public HashMap<String, String> getUserInfo(HashMap<String, String> token) {
			JSONParser parsing = new JSONParser();
			String apiUrl = "https://openapi.naver.com/v1/nid/me";
			HashMap<String,String> userInfo = new HashMap<String,String>();
			URL resUrl;
			try {
				resUrl = new URL(apiUrl);
				String access = token.get("access");
				String header = "Bearer "+access;
				HttpURLConnection con = (HttpURLConnection)resUrl.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization", header);
				BufferedReader br;
				int responseCode = con.getResponseCode();
				if(responseCode == 200) {
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				}else {
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String inputLine;
				StringBuffer res = new StringBuffer();
				while((inputLine = br.readLine()) != null) {
					res.append(inputLine);
				}
				br.close();
				Object finalResult = parsing.parse(res.toString());
				JSONObject fr = (JSONObject)finalResult;
				JSONObject resObj = (JSONObject)fr.get("response");
				userInfo.put("id", (String)resObj.get("id"));
				userInfo.put("mail",(String)resObj.get("email"));
				userInfo.put("name",(String)resObj.get("name"));
				userInfo.put("nickName",(String)resObj.get("nickname"));
				userInfo.put("image",(String)resObj.get("profile_image"));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		return userInfo;
		}
		public int naverLogin(Member m) {
			Connection conn = JDBCTemplate.getConnection();
			MemberDao dao = new MemberDao();
			int result = dao.searchSnsMember(conn,m.getMemberId()); //0 아니면 1이 나오는데 0 이면 인서트 1이면 그냥 반환 
			if(result == 0) {
				// 0이면 DB에 insert
				int inResult = dao.naverLogin(conn,m);
				if(inResult>0) {
					JDBCTemplate.commit(conn);
					result = 1;
				}
				else {
					JDBCTemplate.rollback(conn);
				}
			}
				JDBCTemplate.close(conn);
			return result;
		}
		

	
}

