package model_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.jsp.tagext.PageData;
import javax.sql.DataSource;

public class MemberDAO {
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:/comp/env/CCting");
			con = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 회원 목록
	public ArrayList<MemberDTO> list(){
		
		sql = "select * from member order by mid desc";
		ArrayList<MemberDTO> res = new ArrayList<>();
		
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMid(rs.getString("mid"));
				dto.setMname(rs.getString("mname"));
				dto.setMnic(rs.getString("mnic"));
				dto.setMphone(rs.getString("mphone"));
				dto.setMemail(rs.getString("memail"));
				dto.setMaddress(rs.getString("maddress"));
				dto.setMbirth(rs.getDate("mbirth"));
				dto.setMtype(rs.getString("mtype"));
				
				res.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return res;
	}	
	
	
	public void close() {
		if(rs != null) try {rs.close(); } catch(Exception e) {}
		if(ptmt != null) try {ptmt.close(); } catch(Exception e) {}
		if(con != null) try {con.close(); } catch(Exception e) {}
	}
	
}
