<%@page import="model_p.PageData"%>
<%@page import="model_p.ArtsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	//같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	ArtsDTO dto = (ArtsDTO)request.getAttribute("mainData");
%>
<script>
	function fileDel() {
		myFrm.action="AFileDelete?page=<%=pd.page %>"
		myFrm.submit()
	}
</script>
<!-- 파일이 존재하므로 enctype="multipart/form-data" -->
<form name="myFrm" action="AModifyReg?page=<%=pd.page %>" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value=<%=dto.getId() %>>
	<table border="">
		<tr>
			<td width="100px">작성자</td>
			<td width="800px"><input type="text" name="name" value="<%=dto.getName() %>"></td>
		</tr>
		<tr>
		<!-- 수정 불가 -->
		<!-- 암호로 수정자격 확인 -->
			<td>암호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td>나이</td>
			<td>만 <input type="number" name="age" value="<%=dto.getAge() %>">세</td>
		</tr>
		<tr>
			<td>키</td>
			<td><input type="number" name="height" value="<%=dto.getHeight() %>">cm</td>
		</tr>
		<tr>
			<td>체중</td>
			<td><input type="number" name="weight" value="<%=dto.getWeight() %>">kg</td>
		</tr>
		<tr>
			<td>소속사</td>
			<td><input type="text" name="agency" value="<%=dto.getAgency() %>"></td>
		</tr>
		<tr>
			<td>출연작품</td>
			<td><textarea name="arts" cols="30" rows="10"><%=dto.getArts() %>></textarea></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content"
				cols="30" rows="10"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td>수상내역</td>
			<td><textarea name="awards" cols="30" rows="10"><%=dto.getAwards() %></textarea></td>
		</tr>
		<tr>
			<td>사진1-프로필</td>
			<td>
			<!-- 작성시 파일을 첨부하지 않았다면 -->
			<% if(dto.getPhoto1().equals("")) { %>
				<!-- 새로 파일첨부 가능 -->
				<input type="file" name="photo1" value="<%=dto.getPhoto1() %>">
			<!-- 작성시 파일을 첨부했다면 -->
			<% }else{ %>
				<!-- 파일삭제 가능 -->
				<%=dto.getPhoto1() %><input type="button" value="파일삭제" onclick="fileDel()"/>
			<% } %>
			</td>
		</tr>
		<tr>
			<td>사진2-전신사진</td>
			<td>
				<!-- 작성시 파일을 첨부하지 않았다면 -->
				<% if(dto.getPhoto2().equals("")) { %>
					<!-- 새로 파일첨부 가능 -->
					<input type="file" name="photo2" value="<%=dto.getPhoto2() %>">
				<!-- 작성시 파일을 첨부했다면 -->
				<% }else{ %>
					<!-- 파일삭제 가능 -->
					<%=dto.getPhoto2() %><input type="button" value="파일삭제" onclick="fileDel()"/>
				<% } %>
			</td>
		</tr>
		<tr>
			<td>사진3-작품사진</td>
			<td>
				<!-- 작성시 파일을 첨부하지 않았다면 -->
				<% if(dto.getBfile2().equals("")) { %>
					<!-- 새로 파일첨부 가능 -->
					<input type="file" name="bfile2" value="<%=dto.getBfile2() %>">
				<!-- 작성시 파일을 첨부했다면 -->
				<% }else{ %>
					<!-- 파일삭제 가능 -->
					<%=dto.getBfile2() %><input type="button" value="파일삭제" onclick="fileDel()"/>
				<% } %>
			</td>
		</tr>
		<tr>
			<td>파일1-목소리</td>
			<td><input type="file" name="bfile1" value="<%=dto.getBfile1() %>"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정하기">
				<input type="reset" value="초기화">
				<a href="ADetail?id=<%=dto.getId() %>&page=<%=pd.page %>">뒤로</a>
			</td>
		</tr>
	</table>
</form>
