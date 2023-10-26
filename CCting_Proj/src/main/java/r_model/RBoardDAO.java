package r_model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RBoardDAO {

	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public RBoardDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:/comp/env/CCting");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public ArrayList<RBoardDTO> list(PageData pd){
		
		sql = "select * from review order by gid desc , seq limit ?, ?";
		ArrayList<RBoardDTO> res = new ArrayList<>();
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pd.start);
			ptmt.setInt(2, pd.limit);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				RBoardDTO dto = new RBoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLev(rs.getInt("lev"));
				dto.setNic(rs.getString("nic"));
				dto.setRtype(rs.getString("rtype"));
				dto.setPw(rs.getString("pw"));
				dto.setTitle(rs.getString("title"));
				dto.setUpfile(rs.getString("upfile"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				
				res.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		 
		return res;
	}
	
	public int totalCnt() {
		
		sql = "select count(*) from review";
		int res = 0;
		
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			// 첫번째 값을 res에 넣어라 (즉, board 테이블의 총개수를 말하는것)
			// 괄호 안에 1 말고 "count(*)" 로도 입력가능 (칼럼명으로 입력가능)
			res = rs.getInt(1);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		 
		return res;
	}
	
public RBoardDTO detail(int id){
		
		sql = "select * from review where id = ?";
		RBoardDTO dto = null;
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				dto = new RBoardDTO();
				dto.setId(rs.getInt("id"));
				dto.setGid(rs.getInt("gid"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLev(rs.getInt("lev"));
				dto.setNic(rs.getString("nic"));
				dto.setRtype(rs.getString("rtype"));
				dto.setTitle(rs.getString("title"));
				dto.setUpfile(rs.getString("upfile"));
				dto.setContent(rs.getString("content"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		 
		return dto;
	}
	
	
	public void addCount(int id){
		
		sql = "update review set cnt = cnt + 1 where id = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, id);
			ptmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void write(RBoardDTO dto){
		
		try {
			sql = "select max(id)+1 from review";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			dto.setId(rs.getInt(1));
			
			
			ptmt.close();
			
			sql = "insert into review " +
					"(id, rtype, title, nic, pw, upfile, content, seq, lev, gid, cnt, reg_date) "+ 
					 "values (?, ?, ?, ?, ?, ?, ?, 0, 0, ? ,-1, sysdate() )";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getRtype());
			ptmt.setString(3, dto.getTitle());
			ptmt.setString(4, dto.getNic());
			ptmt.setString(5, dto.getPw());
			ptmt.setString(6, dto.getUpfile());
			ptmt.setString(7, dto.getContent());
			ptmt.setInt(8, dto.getId());
			ptmt.executeUpdate();
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public int delete(RBoardDTO dto){
		
		sql = "delete from review where id = ? and pw = ?";
		int res = 0;
		try {
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			
			res = ptmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	
	public void fileDelete(RBoardDTO dto){
		
		sql = "update review set upfile = null where id = ? and pw = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			
			ptmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	

	public RBoardDTO idPwChk(RBoardDTO dto){
		
		sql = "select * from review where id = ? and pw = ?";
		RBoardDTO res = null;
		try {
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new RBoardDTO();
				res.setId(rs.getInt("id"));
				res.setUpfile(rs.getString("upfile"));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	
	public int modify(RBoardDTO dto){
		
		int res = 0;
		
		sql = "update review set title = ?, nic = ?, upfile = ?, content = ? "+
		" where id = ? and pw = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getNic());
			ptmt.setString(3, dto.getUpfile());
			ptmt.setString(4, dto.getContent());
			ptmt.setInt(5, dto.getId());
			ptmt.setString(6, dto.getPw());
			
			res = ptmt.executeUpdate();
							
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		
		return res;
	}
	
	
	
	
	public void reply(RBoardDTO dto){
		
		try {
			
			sql = "update review set seq = seq +1 where gid = ? and seq > ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getGid());
			ptmt.setInt(2, dto.getSeq());
			
			ptmt.executeUpdate();
			ptmt.close();
			
			
			sql = "select max(id)+1 from review";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			dto.setId(rs.getInt(1));
			
			
			ptmt.close();
			
			sql = "insert into review " +
					"(id, rtype, title, nic, pw, upfile, content, seq, lev, gid, cnt, reg_date) "+ 
					 "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,-1, sysdate() )";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getRtype());
			ptmt.setString(3, dto.getTitle());
			ptmt.setString(4, dto.getNic());
			ptmt.setString(5, dto.getPw());
			ptmt.setString(6, dto.getUpfile());
			ptmt.setString(7, dto.getContent());
			ptmt.setInt(8, dto.getSeq()+1);
			ptmt.setInt(9, dto.getLev()+1);
			ptmt.setInt(10, dto.getGid());
			ptmt.executeUpdate();
			

				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void close() {
		if(rs!=null) try { rs.close();	} catch (Exception e) {}
		if(ptmt!=null) try { ptmt.close();	} catch (Exception e) {}
		if(con!=null) try { con.close();	} catch (Exception e) {}
	}
	
}
