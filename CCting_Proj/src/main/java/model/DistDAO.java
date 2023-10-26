package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DistDAO {
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public DistDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:/comp/env/CCting");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<DistDTO> list(PageData pd){
		sql = "select * from Dist order by gid desc, seq limit ?, ?";
		ArrayList<DistDTO> res = new ArrayList<>();
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, pd.start);
			ptmt.setInt(2, pd.limit);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				DistDTO dto = new DistDTO();
				dto.setDid(rs.getInt("did"));
				dto.setGid(rs.getInt("gid"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setLev(rs.getInt("lev"));
				dto.setSeq(rs.getInt("seq"));
				dto.setDname(rs.getString("dname"));
				dto.setDpw(rs.getString("dpw"));
				dto.setDtype(rs.getString("dtype"));
				dto.setDtitle(rs.getString("dtitle"));
				dto.setDcontent(rs.getString("dcontent"));
				dto.setDist(rs.getString("dist"));
				dto.setDphone(rs.getString("dphone"));
				dto.setDfile1(rs.getString("dfile1"));
				dto.setDfile2(rs.getString("dfile2"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setOpenDate(rs.getString("openDate"));
				dto.setCloseDate(rs.getString("closeDate"));
				
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();		
		}
		
		return res;
	}
	
	public int totalCnt() {
		
		sql = "select count(*) from Dist";
		int totalCnt = 0;
		
		try {
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			totalCnt = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return totalCnt;
	}
	
	public DistDTO detail(int did){
		sql = "select * from Dist where did = ?";		
		DistDTO dto = null;
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, did);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				dto = new DistDTO();
				dto.setDid(rs.getInt("did"));
				dto.setGid(rs.getInt("gid"));
				dto.setCnt(rs.getInt("cnt"));
				dto.setLev(rs.getInt("lev"));
				dto.setSeq(rs.getInt("seq"));
				dto.setDname(rs.getString("dname"));
				dto.setDpw(rs.getString("dpw"));
				dto.setDtype(rs.getString("dtype"));
				dto.setDtitle(rs.getString("dtitle"));
				dto.setDcontent(rs.getString("dcontent"));
				dto.setDist(rs.getString("dist"));
				dto.setDphone(rs.getString("dphone"));
				dto.setDfile1(rs.getString("dfile1"));
				dto.setDfile2(rs.getString("dfile2"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				dto.setOpenDate(rs.getString("openDate"));
				dto.setCloseDate(rs.getString("closeDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();			
		}
		
		return dto;
	}
	
	public void addCount(int did){
		sql = "update Dist set cnt = cnt + 1 where did = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, did);
			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();			
		}
	}
	
	public void write(DistDTO dto){
		
		try {
			sql = "select max(did)+1 from Dist";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			dto.setDid(rs.getInt(1));
			dto.setGid(rs.getInt(1));
			
			ptmt.close();
			
			sql = "insert into Dist(did, dname, dpw, dtype, dtitle, dcontent, dist, dphone, dfile1, dfile2, "
					+ "cnt, seq, lev, gid, reg_date) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, 0, ?, sysdate())";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getDid());
			ptmt.setString(2, dto.getDname());
			ptmt.setString(3, dto.getDpw());
			ptmt.setString(4, dto.getDtype());
			ptmt.setString(5, dto.getDtitle());
			ptmt.setString(6, dto.getDcontent());
			ptmt.setString(7, dto.getDist());
			ptmt.setString(8, dto.getDphone());
			ptmt.setString(9, dto.getDfile1());
			ptmt.setString(10, dto.getDfile2());
			ptmt.setInt(11, dto.getDid());
		
			ptmt.executeUpdate();
			
		
			ptmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();			
		}
	}
	
	public int delete(DistDTO dto) {
		sql = "delete from Dist where did = ? and dpw = ?";
		int res = 0;
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getDid());
			ptmt.setString(2, dto.getDpw());
			res = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public DistDTO idPwChk(DistDTO dto) {
		sql = "select * from Dist where did = ? and dpw = ?";
		DistDTO res = null;
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getDid());
			ptmt.setString(2, dto.getDpw());
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new DistDTO();
				res.setDid(rs.getInt("did"));
				res.setDfile1(rs.getString("dfile1"));
				res.setDfile2(rs.getString("dfile2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public int modify(DistDTO dto){
		
					
		sql = "update Dist set did=?, dname=?, dpw=?, dtype=?, dtitle=?,"
				+ " dcontent=?, dist=?, dphone=?, dfile1=?, dfile2=?,openDate=?, closeDate=? ";
		int res = 0;
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getDid());
			ptmt.setString(2, dto.getDname());
			ptmt.setString(3, dto.getDpw());
			ptmt.setString(4, dto.getDtype());
			ptmt.setString(5, dto.getDtitle());
			ptmt.setString(6, dto.getDcontent());
			ptmt.setString(7, dto.getDist());
			ptmt.setString(8, dto.getDphone());
			ptmt.setString(9, dto.getDfile1());
			ptmt.setString(10, dto.getDfile2());
			ptmt.setString(11, dto.getOpenDate());
			ptmt.setString(12, dto.getCloseDate());
			ptmt.executeUpdate();
			res = ptmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();			
		}
		return res;
	}
	
	public void file1Delete(DistDTO dto) {
		sql = "update Dist set dfile1 = null where did = ? and dpw = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getDid());
			ptmt.setString(2, dto.getDpw());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	public void file2Delete(DistDTO dto) {
		sql = "update Dist set dfile2 = null where did = ? and dpw = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getDid());
			ptmt.setString(2, dto.getDpw());
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void reply(DistDTO dto){
		
		try {
			sql = "update Dist set seq = seq + 1 where gid = ? and seq > ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getGid());
			ptmt.setInt(2, dto.getSeq());
			ptmt.executeUpdate();
			ptmt.close();
			
			sql = "select max(did)+1 from Dist";
			ptmt = con.prepareStatement(sql);
			rs = ptmt.executeQuery();
			rs.next();
			dto.setDid(rs.getInt(1));
			
			ptmt.close();
			
			sql = "insert into Dist(did, dname, dpw, dtype, dtitle, dcontent, dist, dphone, dfile1, dfile2, "
					+ "cnt, seq, lev, gid, reg_date,openDate, closeDate) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, ?, ?, sysdate(), ?, ?)";
					
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.getDid());
			ptmt.setString(2, dto.getDname());
			ptmt.setString(3, dto.getDpw());
			ptmt.setString(4, dto.getDtype());
			ptmt.setString(5, dto.getDtitle());
			ptmt.setString(6, dto.getDcontent());
			ptmt.setString(7, dto.getDist());
			ptmt.setString(8, dto.getDphone());
			ptmt.setString(9, dto.getDfile1());
			ptmt.setString(10, dto.getDfile2());
			ptmt.setInt(11, dto.getSeq()+1);
			ptmt.setInt(12, dto.getLev()+1);
			ptmt.setInt(13, dto.getDid());
			ptmt.setString(14, dto.getOpenDate());
			ptmt.setString(15, dto.getCloseDate());
			ptmt.executeUpdate();
			
			ptmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();			
		}
	}
	
	public void close() {
		if(rs!=null) {try {rs.close();}catch(Exception e) {}}
		if(ptmt!=null) {try {ptmt.close();}catch(Exception e) {}}
		if(con!=null) {try {con.close();}catch(Exception e) {}}
	}
}
