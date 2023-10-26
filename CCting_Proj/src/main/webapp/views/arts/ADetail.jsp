<%@page import="model_p.PageData"%>
<%@page import="model_p.ArtsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	// 같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	ArtsDTO dto = (ArtsDTO)request.getAttribute("mainData");
%>
<table border="">
	<tr>
		<td width="200px">작성자</td>
		<td width="700px"><%=dto.getName() %></td>
	</tr>
	<tr>
		<td>작성일</td>
		<td><%=dto.getEdit_dateStr() %></td>
	</tr>
	<tr>
		<td>나이</td>
		<td>만 <%=dto.getAge() %>세</td>
	</tr>
	<tr>
		<td>키</td>
		<td><%=dto.getHeight() %>cm</td>
	</tr>
	<tr>
		<td>체중</td>
		<td><%=dto.getWeight() %>kg</td>
	</tr>
	<tr>
		<td>소속사</td>
		<td><%=dto.getAgency() %></td>
	</tr>
	<tr>
		<td>출연작품</td>
		<td><%=dto.getArtsBr() %></td>
	</tr>
	<tr>
		<td>수상내역</td>
		<td><%=dto.getAwardsBr() %></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><%=dto.getContentBr() %></td>
	</tr>
	<tr>
		<td>사진1-프로필</td>
		<td><img src="\CCting_Proj\up\<%=dto.getPhoto1()%>" alt="프로필"></td>
	</tr>
	<tr>
		<td>사진2-전신사진</td>
		<td><img src="/CCting_Proj/up/<%=dto.getPhoto2()%>" alt="전신"></td>
	</tr>
	<tr>
		<td>사진3-출연작</td>
		<td><img src="/CCting_Proj/up/<%=dto.getBfile2()%>" alt="출연작"></td>
	</tr>
	<tr>
		<td>파일1-목소리</td>
		<td><a href="/CCting_Proj/file/FileDown?fName=<%=dto.getBfile1() %>"><%=dto.getBfile1() %></a></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<a href="AList?page=<%=pd.page %>">목록으로</a>
			<a href="ADeleteForm?id=<%=dto.getId() %>&page=<%=pd.page %>">삭제</a>
			<a href="AModifyForm?id=<%=dto.getId() %>&page=<%=pd.page %>">수정</a>
		</td>
	</tr>
</table>