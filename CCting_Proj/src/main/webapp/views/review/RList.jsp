<%@page import="r_model.PageData"%>
<%@page import="r_model.RBoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table border="">
	<tr align="center">
		<td width="50px">번호</td>
		<td width="50px">분류</td>
		<td width="450px">제목</td>
		<td width="200px">작성일</td>
		<td width="100px">작성자</td>
		<td width="50px">조회수</td>
	</tr>
<%

// PageData : model_p 패키지에 만들어준 클래스임
PageData pd = (PageData)request.getAttribute("pd");
int i = pd.start;
for(RBoardDTO dto : (ArrayList<RBoardDTO>) request.getAttribute("mainData")) {
i++;

%>	
	<tr align="center">
		<td><%=i %></td>
		<td><%=dto.getRtype() %></td>
		<td align="left">
		<% for(int n=0; n<dto.getLev(); n++) { %>
				&nbsp;&nbsp;
		<% } if(dto.getLev()>0) { %>
		└
		<% } %>
			<a href="RDetail?id=<%=dto.getId()%>&page=<%=pd.page%>"><%=dto.getTitle() %></a>
		</td>
		<td><%=dto.getReg_date() %></td>
		<td><%=dto.getNic() %></td>
		<td><%=dto.getCnt() %></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="6"  align="center">
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
		<td colspan="6"  align="right">
			<a href="RWriteForm?page=<%=pd.page%>">글쓰기</a>
		</td>
	</tr>
</table>