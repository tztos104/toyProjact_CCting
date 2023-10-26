package model_p;

import java.util.Date;

public class MemberDTO {

	String mid, mname, mnic, mphone, memail, maddress, mtype;
	Date mbirth;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMnic() {
		return mnic;
	}
	public void setMnic(String mnic) {
		this.mnic = mnic;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMaddress() {
		return maddress;
	}
	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public Date getMbirth() {
		return mbirth;
	}
	public void setMbirth(Date mbirth) {
		this.mbirth = mbirth;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [mid=" + mid + ", mname=" + mname + ", mnic=" + mnic + ", mphone=" + mphone + ", memail="
				+ memail + ", maddress=" + maddress + ", mtype=" + mtype + ", mbirth=" + mbirth + "]";
	}
	
	
	
}
