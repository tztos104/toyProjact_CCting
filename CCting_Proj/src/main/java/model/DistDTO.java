package model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class DistDTO {
	int did, cnt, seq, lev, gid;
	String dname, dpw, dtype, dtitle, dcontent, dist, dphone, dfile1, dfile2,openDate, closeDate;; 
	Date reg_date;
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd (E) HH:mm");
	SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDpw() {
		return dpw;
	}
	public void setDpw(String dpw) {
		this.dpw = dpw;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getDtitle() {
		return dtitle;
	}
	public void setDtitle(String dtitle) {
		this.dtitle = dtitle;
	}
	public String getDcontent() {
		return dcontent;
	}
	public String getDcontentbr() {
		return dcontent.replaceAll("\n", "<br/>");
	}
	public void setDcontent(String dcontent) {
		this.dcontent = dcontent;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getDphone() {
		return dphone;
	}
	public void setDphone(String dphone) {
		this.dphone = dphone;
	}
	public String getDfile1() {
		if (dfile1 == null || dfile1.trim().equals("") || dfile1.trim().equals("null")) {
			dfile1 = "";
		}
		return dfile1;
		
	}
	public void setDfile1(String dfile1) {
		this.dfile1 = dfile1;
	}
	public String getDfile2() {
		if (dfile2 == null || dfile2.trim().equals("") || dfile2.trim().equals("null")) {
			dfile2 = "";
		}
		return dfile2;
	}
	public void setDfile2(String dfile2) {
		this.dfile2 = dfile2;
	}
	public String getOpenDate() {
		return  fmt2.format(openDate);
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getCloseDate() {
		return fmt2.format(closeDate);
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}
	
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public boolean getIsImg() {
		boolean isImg = Pattern.matches(".*[.](jpg|bmp|png|gif)", getDfile1().toLowerCase());
		return isImg;
	}
	public boolean getIsAvi() {
		boolean isImg = Pattern.matches(".*[.](avi|mp4|wmv|mpeg4)", getDfile1().toLowerCase());
		return isImg;
	}
	
	
	public String getReg_datestr() {
		return fmt.format(reg_date);
	}
	
	
}
