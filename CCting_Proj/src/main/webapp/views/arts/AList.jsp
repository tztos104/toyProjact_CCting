<%@page import="model_p.PageData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model_p.ArtsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="" width="100%">
	<tr align="center">
		<td>번호</td>
		<td>작성자</td>
		<td>작성일</td>
	</tr>
	<%
	PageData pd = (PageData)request.getAttribute("pd");
	int i=pd.start;
	// 같은 이름의 클래스에서 넘겨받은 메인데이터 출력
	for(ArtsDTO dto : (ArrayList<ArtsDTO>)request.getAttribute("mainData")) {
		i++; 
	%>
	<tr align="center">
		<td><%=i %></td>
		<td align="left">
			<a href="ADetail?id=<%=dto.getId() %>&page=<%=pd.page %>"><img src="\CCting_Proj\up\<%=dto.getPhoto1()%>" alt="프로필"></a>
		</td>
		<td><%=dto.getEdit_dateStr() %></td>
	</tr>
	<% } %>
	<tr>
		<td colspan="3" align="center">
		<!-- 현재 페이지 범위의 시작번호가 1이 아니면 이전글자가 나오게 함 -->
		<% if(pd.pageStart>1) { %>
			<a href="?page=<%=pd.pageStart-1 %>">[이전]</a>
		<!-- 페이지 범위의 시작부터 끝까지 돌면서 해당페이지면 [p] -->
		<% } for(int p=pd.pageStart; p<=pd.pageEnd; p++) {
				if(pd.page == p) { %>
					[<%=p %>]
			<% }else{ %>
					<a href="?page=<%=p %>"><%=p %></a>
		<% }} if(pd.pageEnd < pd.pageTotal) { %>
				<a href="?page=<%=pd.pageEnd+1 %>">[다음]</a>
		<% } %>
		</td>
	</tr>
	<tr>
		<td colspan="5" align="right"><a href="AWriteForm?page=<%=pd.page %>">글쓰기</a></td>
	</tr>
</table>