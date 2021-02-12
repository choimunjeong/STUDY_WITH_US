package groupstudy.model.vo;

public class GroupStudyMember {
	private int groupMemberNo;
	private int memberNo;
	private int groupNo;
	public GroupStudyMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupStudyMember(int groupMemberNo, int memberNo, int groupNo) {
		super();
		this.groupMemberNo = groupMemberNo;
		this.memberNo = memberNo;
		this.groupNo = groupNo;
	}
	public int getGroupMemberNo() {
		return groupMemberNo;
	}
	public void setGroupMemberNo(int groupMemberNo) {
		this.groupMemberNo = groupMemberNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	
}
