package groupstudy.model.vo;

import java.util.ArrayList;
import java.util.HashMap;

import member.model.vo.Member;

public class GroupCommentMemberFilePath {
	private ArrayList<GroupComment> gcList;
	private HashMap<String, String> memberIdFileMap;
	public GroupCommentMemberFilePath() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupCommentMemberFilePath(ArrayList<GroupComment> gcList, HashMap<String, String> memberIdFileMap) {
		super();
		this.gcList = gcList;
		this.memberIdFileMap = memberIdFileMap;
	}
	public ArrayList<GroupComment> getGcList() {
		return gcList;
	}
	public void setGcList(ArrayList<GroupComment> gcList) {
		this.gcList = gcList;
	}
	public HashMap<String, String> getMemberIdFileMap() {
		return memberIdFileMap;
	}
	public void setMemberIdFileMap(HashMap<String, String> memberIdFileMap) {
		this.memberIdFileMap = memberIdFileMap;
	}
	
	
	
	
}
