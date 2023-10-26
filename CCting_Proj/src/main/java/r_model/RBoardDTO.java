package r_model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class RBoardDTO {
	
	String rtype, title, nic, pw, upfile, content;
	Date reg_date;
	int id, cnt, seq, lev, gid;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (E) HH:mm");
	
	
	public String getRtype() {
		return rtype;
	}
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNic() {
		return nic;
	}
	public void setNic(String nic) {
		this.nic = nic;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUpfile() {
		
		if(	upfile==null || 
				upfile.trim().equals("") || 
				upfile.trim().equals("null")) {
				upfile = "";
		}
			
		return upfile;
		
	}
	
	public boolean isImg() {
		boolean res = Pattern.matches(".*[.](jpg|bmp|png|gif)", getUpfile().toLowerCase());
		
		return res;
	}
	
	public void setUpfile(String upfile) {
		this.upfile = upfile;
	}
	public String getContent() {
		return content;
	}
	
	public String getContentBr() {
		return content.replaceAll("\n", "<br/>");
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	
	public String getReg_dateStr() {
		return sdf.format(reg_date);
	}
	
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	@Override
	public String toString() {
		return "RBoardDTO [id=" + id + ", rtype=" + rtype + ", title=" + title + ", nic=" + nic + ", pw=" + pw
				+ ", upfile=" + upfile + ", content=" + content + ", reg_date=" + reg_date + ", cnt=" + cnt + ", seq="
				+ seq + ", lev=" + lev + ", gid=" + gid + ", sdf=" + sdf + "]";
	}

	
	
	
	
}
