package groupstudy.model.vo;

public class GroupStudyRoomAddCategory {
	private GroupStudyRoom gsr;
	private Category category;
	public GroupStudyRoomAddCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GroupStudyRoomAddCategory(GroupStudyRoom gsr, Category category) {
		super();
		this.gsr = gsr;
		this.category = category;
	}
	public GroupStudyRoom getGsr() {
		return gsr;
	}
	public void setGsr(GroupStudyRoom gsr) {
		this.gsr = gsr;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
}
