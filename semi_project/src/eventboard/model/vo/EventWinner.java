package eventboard.model.vo;

public class EventWinner {
	private int winnerNo;
	private int eventNo;
	private int memberNo;
	public EventWinner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventWinner(int winnerNo, int eventNo, int memberNo) {
		super();
		this.winnerNo = winnerNo;
		this.eventNo = eventNo;
		this.memberNo = memberNo;
	}
	public int getWinnerNo() {
		return winnerNo;
	}
	public void setWinnerNo(int winnerNo) {
		this.winnerNo = winnerNo;
	}
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	
	
	
	
}
