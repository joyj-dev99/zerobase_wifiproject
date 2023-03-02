package wifiDto;

import java.util.List;

public class wifiGson {
	private int list_total_count;
	private List<resultItem> LESULT;
	private List<rowItem> row;
	
	
	public int getList_total_count() {
		return list_total_count;
	}
	public void setList_total_count(int list_total_count) {
		this.list_total_count = list_total_count;
	}
	public List<resultItem> getLESULT() {
		return LESULT;
	}
	public void setLESULT(List<resultItem> lESULT) {
		LESULT = lESULT;
	}
	public List<rowItem> getRow() {
		return row;
	}
	public void setRow(List<rowItem> row) {
		this.row = row;
	}
}