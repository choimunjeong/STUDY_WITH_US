package groupstudy.model.vo;

import java.util.ArrayList;

public class GroupStudyViewData {
	private GroupStudyRoom gsr;
	private ArrayList<GroupComment> list;
	private GroupApply gApply;
	private GroupStudyMember gMem;
	public GroupStudyViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupStudyViewData(GroupStudyRoom gsr, ArrayList<GroupComment> list, GroupApply gApply,
			GroupStudyMember gMem) {
		super();
		this.gsr = gsr;
		this.list = list;
		this.gApply = gApply;
		this.gMem = gMem;
	}
	public GroupStudyRoom getGsr() {
		return gsr;
	}
	public void setGsr(GroupStudyRoom gsr) {
		this.gsr = gsr;
	}
	public ArrayList<GroupComment> getList() {
		return list;
	}
	public void setList(ArrayList<GroupComment> list) {
		this.list = list;
	}
	public GroupApply getgApply() {
		return gApply;
	}
	public void setgApply(GroupApply gApply) {
		this.gApply = gApply;
	}
	public GroupStudyMember getgMem() {
		return gMem;
	}
	public void setgMem(GroupStudyMember gMem) {
		this.gMem = gMem;
	}
	
}
