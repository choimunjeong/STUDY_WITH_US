package member.model.vo;

import java.util.ArrayList;

import member.model.vo.Member;

public class MemberManagePage {
	private ArrayList<Member> list;
	private String pageNavi;
	public MemberManagePage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberManagePage(ArrayList<Member> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Member> getList() {
		return list;
	}
	public void setList(ArrayList<Member> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
