package alarm.model.vo;

public class Alarm {
	private int alarmNo;
	private String alarmContent;
	private int memberNo;
	private int groupNo;
	private String alarmStatus;
	private int alarmSubject;
	private int rNum;
	private int sendMemberNo;
	
	public Alarm(int alarmNo, String alarmContent, int memberNo, int groupNo, String alarmStatus, int alarmSubject,
			int rNum, int sendMemberNo) {
		super();
		this.alarmNo = alarmNo;
		this.alarmContent = alarmContent;
		this.memberNo = memberNo;
		this.groupNo = groupNo;
		this.alarmStatus = alarmStatus;
		this.alarmSubject = alarmSubject;
		this.rNum = rNum;
		this.sendMemberNo = sendMemberNo;
	}


	public Alarm() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public int getAlarmNo() {
		return alarmNo;
	}
	public void setAlarmNo(int alarmNo) {
		this.alarmNo = alarmNo;
	}
	public String getAlarmContent() {
		return alarmContent;
	}
	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
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
	public String getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public int getAlarmSubject() {
		return alarmSubject;
	}
	public void setAlarmSubject(int alarmSubject) {
		this.alarmSubject = alarmSubject;
	}


	public int getSendMemberNo() {
		return sendMemberNo;
	}


	public void setSendMemberNo(int sendMemberNo) {
		this.sendMemberNo = sendMemberNo;
	}
	
	
}
