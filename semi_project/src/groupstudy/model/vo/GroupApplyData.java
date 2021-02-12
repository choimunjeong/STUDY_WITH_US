package groupstudy.model.vo;

public class GroupApplyData {
	private int groupNo;
	private String groupTitle;
	private int groupMax;
	private String applyContent;
	private String memberNickname;
	private int applyMemberNo;
	
	
	public int getApplyMemberNo() {
		return applyMemberNo;
	}
	public void setApplyMemberNo(int applyMemberNo) {
		this.applyMemberNo = applyMemberNo;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	public int getGroupMax() {
		return groupMax;
	}
	public void setGroupMax(int groupMax) {
		this.groupMax = groupMax;
	}
	public String getApplyContent() {
		return applyContent;
	}
	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public GroupApplyData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupApplyData(int groupNo, String groupTitle, int groupMax, String applyContent, String memberNickname,
			int applyMemberNo) {
		super();
		this.groupNo = groupNo;
		this.groupTitle = groupTitle;
		this.groupMax = groupMax;
		this.applyContent = applyContent;
		this.memberNickname = memberNickname;
		this.applyMemberNo = applyMemberNo;
	}

	
}
