package model_p;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArtsDTO {
	String pw, name, agency, arts, content, awards, photo1, photo2, bfile1, bfile2;
	Date edit_date;
	int id, age, height, weight;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(E) hh:mm");


	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getArts() {
		return arts;
	}
	
	public String getArtsBr() {
		if(arts!=null) {			
			return arts.replaceAll("\n", "<br>");
		}
		return arts;
	}

	public void setArts(String arts) {
		this.arts = arts;
	}

	public String getContent() {
		return content;
	}
	
	public String getContentBr() {
		if(content!=null) {			
			return content.replaceAll("\n", "<br>");
		}
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAwards() {
		return awards;
	}

	public String getAwardsBr() {
		if(awards!=null) {			
			return awards.replaceAll("\n", "<br>");
		}
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public String getPhoto1() {
// 파일이 없거나 공백이거나 null이라는 문자라면 공백으로 통일시킴
		if(photo1==null
				|| photo1.trim().equals("")
				|| photo1.trim().equals("null")) {
			photo1 = "";
		}
		return photo1;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public String getPhoto2() {
// 파일이 없거나 공백이거나 null이라는 문자라면 공백으로 통일시킴
		if(photo2==null
				|| photo2.trim().equals("")
				|| photo2.trim().equals("null")) {
			photo2 = "";
		}
		return photo2;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public String getBfile1() {
// 파일이 없거나 공백이거나 null이라는 문자라면 공백으로 통일시킴
		if(bfile1==null
				|| bfile1.trim().equals("")
				|| bfile1.trim().equals("null")) {
			bfile1 = "";
		}
		return bfile1;
	}

	public void setBfile1(String bfile1) {
		this.bfile1 = bfile1;
	}

	public String getBfile2() {
// 파일이 없거나 공백이거나 null이라는 문자라면 공백으로 통일시킴
		if(bfile2==null
				|| bfile2.trim().equals("")
				|| bfile2.trim().equals("null")) {
			bfile2 = "";
		}
		return bfile2;
	}

	public void setBfile2(String bfile2) {
		this.bfile2 = bfile2;
	}

	public Date getEdit_date() {
		return edit_date;
	}
	
	public String getEdit_dateStr() {
		return sdf.format(edit_date);
	}

	public void setEdit_date(Date edit_date) {
		this.edit_date = edit_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "ArtsDTO [pw=" + pw + ", name=" + name + ", agency=" + agency + ", arts=" + arts + ", content=" + content + ", awards="
				+ awards + ", photo1=" + photo1 + ", photo2=" + photo2 + ", bfile1=" + bfile1 + ", bfile2=" + bfile2
				+ ", edit_date=" + edit_date + ", id=" + id + ", age=" + age + ", height=" + height + ", weight="
				+ weight + "]";
	}

}
