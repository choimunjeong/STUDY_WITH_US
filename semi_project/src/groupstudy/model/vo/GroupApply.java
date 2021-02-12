package groupstudy.model.vo;

public class GroupApply {
	private int applyNo;
	private int groupNo;
	private String memberNo;
	private String applyContent;
	private int applyStatus;
	public GroupApply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupApply(int applyNo, int groupNo, String memberNo, String applyContent, int applyStatus) {
		super();
		this.applyNo = applyNo;
		this.groupNo = groupNo;
		this.memberNo = memberNo;
		this.applyContent = applyContent;
		this.applyStatus = applyStatus;
	}
	public int getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(int applyNo) {
		this.applyNo = applyNo;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getApplyContent() {
		return applyContent;
	}
	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}
	public int getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(int applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	
}
