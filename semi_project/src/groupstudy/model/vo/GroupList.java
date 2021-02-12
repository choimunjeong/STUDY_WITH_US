package groupstudy.model.vo;

public class GroupList {
	private String groupTitle;
	private String groupStartDate;
	private String groupEndDate;
	private int groupMax;
	private int memberNo;
	private int memberCnt;
	private int groupNo;
	private int rNum;
	
	
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getMemberCnt() {
		return memberCnt;
	}
	public void setMemberCnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	public String getGroupStartDate() {
		return groupStartDate;
	}
	public void setGroupStartDate(String groupStartDate) {
		this.groupStartDate = groupStartDate;
	}
	public String getGroupEndDate() {
		return groupEndDate;
	}
	public void setGroupEndDate(String groupEndDate) {
		this.groupEndDate = groupEndDate;
	}
	public int getGroupMax() {
		return groupMax;
	}
	public void setGroupMax(int groupMax) {
		this.groupMax = groupMax;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public GroupList(String groupTitle, String groupStartDate, String groupEndDate, int groupMax, int memberNo,
			int memberCnt, int groupNo, int rNum) {
		super();
		this.groupTitle = groupTitle;
		this.groupStartDate = groupStartDate;
		this.groupEndDate = groupEndDate;
		this.groupMax = groupMax;
		this.memberNo = memberNo;
		this.memberCnt = memberCnt;
		this.groupNo = groupNo;
		this.rNum = rNum;
	}
	public GroupList() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
}
