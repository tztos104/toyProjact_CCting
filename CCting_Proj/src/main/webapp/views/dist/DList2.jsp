<%@page import="model.DistDTO"%>
<%@page import="model.PageData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="">
	<tr align="center">
		<td width="50px">번호</td>
		<td width="500px">제목</td>
		<td width="100px">작성자</td>
		<td width="200px">작성일</td>
		<td width="50px">조회수</td>
	</tr>
<%

// PageData : model_p 패키지에 만들어준 클래스임
PageData pd = (PageData)request.getAttribute("pd");
int i = pd.start;
for(DistDTO dto : (ArrayList<DistDTO>) request.getAttribute("mainData")) {
i++;
%>	
	<tr align="center">
		<td><%=i %></td>
		<td align="left">
		<% for(int n=0; n<dto.getLev(); n++) { %>
				&nbsp;&nbsp;
		<% } if(dto.getLev()>0) { %>
		└
		<% } %>
			<a href="Ddetail?did=<%=dto.getDid()%>&page=<%=pd.page %>"><%=dto.getDtitle() %></a>
		</td>
		<td><%=dto.getDname() %></td>
		<td><%=dto.getReg_datestr() %></td>
		<td><%=dto.getCnt() %></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="5"  align="center">
		<% if(pd.pageStart > 1) { %>
			<a href="?page=<%=pd.pageStart-1%>">[이전]</a>
		<% } for(int p = pd.pageStart; p <= pd.pageEnd; p++) { 
			if(pd.page == p) {%>
			[<%=p %>]
		<% } else { %>
				<a href = "?page=<%=p %>"><%=p %></a>
		<% }} if (pd.pageEnd < pd.pageTotal) { %>
			<a href="?page=<%=pd.pageEnd+1%>">[다음]</a>
		<% } %>
		</td>
		
	</tr>
	<tr>
		<td colspan="5"  align="right">
			<a href="DwriteForm">글쓰기</a>
		</td>
	</tr>
</table>