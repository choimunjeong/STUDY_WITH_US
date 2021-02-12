package member.model.vo;

import java.util.ArrayList;

import alarm.model.vo.Alarm;

public class AlarmPageData {

		private ArrayList<Alarm> list;
		private String pageNavi;
		public ArrayList<Alarm> getList() {
			return list;
		}
		public void setList(ArrayList<Alarm> list) {
			this.list = list;
		}
		public String getPageNavi() {
			return pageNavi;
		}
		public void setPageNavi(String pageNavi) {
			this.pageNavi = pageNavi;
		}
		public AlarmPageData(ArrayList<Alarm> list, String pageNavi) {
			super();
			this.list = list;
			this.pageNavi = pageNavi;
		}
		public AlarmPageData() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
