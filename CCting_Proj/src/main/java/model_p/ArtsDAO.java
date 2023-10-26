package model_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ArtsDAO {
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public ArtsDAO() {
		try {
			// javax.naming.InitialContext 클래스: 네이밍 시스템에서 객체를 찾기 위한 기본 컨텍스트(Initial Context)를 생성
			Context init = new InitialContext();
			// 연결관리 커넥션제공 객체 ds = InitialContext 객체의. lookup()메서드를 이용하여 이름으로 등록된 DataSource 객체를 찾음("java:/comp/env/" 네임스페이스에 등록된 "1234"라는 이름의 database)
			DataSource ds = (DataSource)init.lookup("java:/comp/env/CCting");
			// DataSource 객체로부터 db 커넥션을 얻어옴
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글목록, 게시판(페이지정보 모델)
	public ArrayList<ArtsDTO> list(PageData pd) {
		// 내림차순으로
		// limit 추가
		sql = "select * from arts order by edit_date desc limit ?, ?";
		// 배열리스트 생성
		ArrayList<ArtsDTO> res = new ArrayList<>();
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 페이지당 시작번호부터 limit만큼씩 가져옴
			ptmt.setInt(1, pd.start);
			ptmt.setInt(2, pd.limit);
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 더 이상의 레코드가 존재하는 동안에 계속해서 반복하는 반복문다음 레코드로 이동, 다음 레코드가 존재하면 true를 반환
			while(rs.next()) {
				// 게시글 모델 생성
				ArtsDTO dto = new ArtsDTO();
				// 게시글의 필드들을 db값을 가져와 세팅
				dto.setId(rs.getInt("id"));
				dto.setPw(rs.getString("pw"));
				dto.setAge(rs.getInt("age"));
				dto.setHeight(rs.getInt("height"));
				dto.setWeight(rs.getInt("weight"));
				dto.setName(rs.getString("name"));
				dto.setAgency(rs.getString("agency"));
				dto.setArts(rs.getString("arts"));
				dto.setContent(rs.getString("content"));
				dto.setAwards(rs.getString("awards"));
				dto.setPhoto1(rs.getString("photo1"));
				dto.setPhoto2(rs.getString("photo2"));
				dto.setBfile1(rs.getString("bfile1"));
				dto.setBfile2(rs.getString("bfile2"));
				dto.setEdit_date(rs.getTimestamp("edit_date"));
				// res목록에 게시글 추가시킴
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 연결종료
			close();
		}
		// 게시글목록 반환
		return res;
	}
	
	// 전체 게시글 수
	public int totalCnt() {
		// arts의 자료 수를 셈
		sql = "select count(*) from arts";
		int res = 0;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 레코드가 있다면
			rs.next();
			// 전체 자료의 수를 res에 부여
			res = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// 전체 자료의 수를 반환
		return res;
	}
	
	// 게시글 상세보기(글 아이디)
	public ArtsDTO detail(int id) {
		// 글 아이디로 검색
		sql = "select * from arts where id = ?";
		// 게시글 모델 null로(리턴하기 위해 if문 밖에서 생성)
		ArtsDTO dto = null;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setInt(1, id);
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 레코드가 있다면
			if(rs.next()) {
				// 게시글 모델 생성
				dto = new ArtsDTO();
				// 게시글의 필드들을 db값을 가져와 세팅
				dto.setId(rs.getInt("id"));
				dto.setAge(rs.getInt("age"));
				dto.setHeight(rs.getInt("height"));
				dto.setWeight(rs.getInt("weight"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setAgency(rs.getString("agency"));
				dto.setArts(rs.getString("arts"));
				dto.setContent(rs.getString("content"));
				dto.setAwards(rs.getString("awards"));
				dto.setPhoto1(rs.getString("photo1"));
				dto.setPhoto2(rs.getString("photo2"));
				dto.setBfile1(rs.getString("bfile1"));
				dto.setBfile2(rs.getString("bfile2"));
				dto.setEdit_date(rs.getTimestamp("edit_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// id로 검색된 db값으로 세팅된 게시글 반환
		return dto;
	}
	
	// 글 작성하기(게시글 모델)
	public void write(ArtsDTO dto) {
		try {
			// 내가 쓴 글을 확인하기 위해 내가 쓴 글 id를 받아와야 함
			// db의 id 중 제일 큰 값+1을 가져옴
			// 아직 글을 안썼으니까 쓴다면 글 id가 제일 큰 값+1이 될 것
			sql = "select max(id)+1 from arts";
			
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 해당 레코드로 이동
			rs.next();
			// 게시글 모델에 rs의 첫번째 값(id)을 세팅함
			dto.setId(rs.getInt(1));

			// 일단 종료
			ptmt.close();

			sql = "insert into arts (id, age, height, weight, name, agency, arts, content, awards, photo1, photo2, bfile1, bfile2, pw, edit_date) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate())";
			
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setInt(1, dto.getId());
			ptmt.setInt(2, dto.getAge());
			ptmt.setInt(3, dto.getHeight());
			ptmt.setInt(4, dto.getWeight());
			ptmt.setString(5, dto.getName());
			ptmt.setString(6, dto.getAgency());
			ptmt.setString(7, dto.getArts());
			ptmt.setString(8, dto.getContent());
			ptmt.setString(9, dto.getAwards());
			ptmt.setString(10, dto.getPhoto1());
			ptmt.setString(11, dto.getPhoto2());
			ptmt.setString(12, dto.getBfile1());
			ptmt.setString(13, dto.getBfile2());
			ptmt.setString(14, dto.getPw());
			// db에서 쿼리실행하고 결과로 영향받은 행의 수를 반환
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	// 글 삭제하기(게시글 모델)
	public int delete(ArtsDTO dto) {
		sql = "delete from arts where id = ? and pw = ?";
		// 결과값을 0으로 선언
		int res = 0;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			// db에서 쿼리실행하고 결과로 영향받은 행의 수를 반환
			res = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// 결과값(영향받은 행의 수가 1개면 성공, 0이면 실패)
		return res;
	}
	
	// 글 수정시 파일삭제(게시글 모델)
	public void fileDelete(ArtsDTO dto){
		// 아이디와 비번으로 검색해서 upfile을 null로 바꿈
		sql = "update arts set upfile = null where id = ? and pw = ?";
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅 
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			// db에서 쿼리실행하고 결과로 영향받은 행의 수를 반환
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
	// 글 삭제시 비번체크(게시글 모델)
	public ArtsDTO idPwChk(ArtsDTO dto) {
		// 게시글아이디와 비번으로 게시글을 검색
		sql = "select * from arts where id = ? and pw = ?";
		// 게시글 모델 null로(리턴하기 위해 if문 밖에서 생성)
		ArtsDTO res = null;
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setInt(1, dto.getId());
			ptmt.setString(2, dto.getPw());
			// db에서 쿼리실행하고 결과로 result set 객체를 반환받음
			rs = ptmt.executeQuery();
			// ResultSet에 레코드가 있다면
			if(rs.next()) {
				// 게시글 모델 생성
				res = new ArtsDTO();
				// 게시글의 필드들을 db값을 가져와 세팅
				res.setId(rs.getInt("id"));
				res.setPhoto1(rs.getString("photo1"));
				res.setPhoto2(rs.getString("photo2"));
				res.setBfile1(rs.getString("bfile1"));
				res.setBfile2(rs.getString("bfile2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// id와 pw로 검색된 db값으로 id와 파일정보가 세팅된 게시글 반환
		return res;
	}

	// 글 수정하기(게시글 모델)
	public int modify(ArtsDTO dto) {
		int res = 0;
		sql = "update arts set name= ?, age = ?, height = ?, weight = ?, agency = ?, arts = ?, content = ?, awards = ?, photo1 = ?, photo2 = ?, bfile1 = ?, bfile2 = ?, edit_date = sysdate() where id = ? and pw = ?";
		try {
			// sql문을 준비(나중에 쿼리실행할 때 사용할 예정)
			ptmt = con.prepareStatement(sql);
			// 물음표부분 세팅
			ptmt.setString(1, dto.getName());
			ptmt.setInt(2, dto.getAge());
			ptmt.setInt(3, dto.getHeight());
			ptmt.setInt(4, dto.getWeight());
			ptmt.setString(5, dto.getAgency());
			ptmt.setString(6, dto.getArts());
			ptmt.setString(7, dto.getContent());
			ptmt.setString(8, dto.getAwards());
			ptmt.setString(9, dto.getPhoto1());
			ptmt.setString(10, dto.getPhoto2());
			ptmt.setString(11, dto.getBfile1());
			ptmt.setString(12, dto.getBfile2());
			ptmt.setInt(13, dto.getId());
			ptmt.setString(14, dto.getPw());
			// db에서 쿼리실행하고 결과로 영향받은 행의 수를 반환
			res = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		// 결과값(영향받은 행의 수가 1개면 성공, 0이면 실패)
		return res;
	}
	
	// 데이터베이스 접속 종료
	public void close() {
		// 뭔가 담겨있다면 끝을 내줘라
		if(rs!=null) try {rs.close();} catch (Exception e) {}
		if(ptmt!=null) try {ptmt.close();} catch (Exception e) {}
		if(con!=null) try {con.close();} catch (Exception e) {}
	}

}

