<%@page import="r_model.RBoardDTO"%>
<%@page import="r_model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	RBoardDTO dto = (RBoardDTO)request.getAttribute("mainData");
%>    
<table border="">
	<tr>
		<td width="150px">번호 : <%=dto.getId() %></td>
		<td width="250px">분류 : <%=dto.getRtype() %></td>
		<td width="500px">제목 : <%=dto.getTitle() %></td>
	</tr>
	<tr>
		<td>작성자 : <%=dto.getNic() %></td>
		<td>작성일 : <%=dto.getReg_dateStr() %></td>
		<td>조회수 : <%=dto.getCnt() %></td>
	</tr>
	<tr>
		<td height="300px;">내용</td>
		<td colspan="2" ><%=dto.getContentBr() %>
		<% if(!dto.getUpfile().equals("")) {
		    if(dto.isImg()) {%>
		<br><img width="500px" src="/CCting_Proj/up/<%=dto.getUpfile() %>" alt="" /> 
		<%} else { %>
		</td>
	</tr>
	<tr>
		<td>파일</td>
		<td colspan="2">
		 	<a href="/CCting_Proj/noneJsp/FileDown?fName=<%=dto.getUpfile() %>"><%=dto.getUpfile() %></a> 
		<%} %>
		</td>
	</tr>
	<%} %>

	<tr>
		<td colspan="3"  align="right">
			<a href="RList?page=<%=pd.page%>">목록으로</a>
			<a href="RDeleteForm?id=<%=dto.getId()%>&page=<%=pd.page%>">삭제</a>
			<a href="RModifyForm?id=<%=dto.getId()%>&page=<%=pd.page%>">수정</a>
			<a href="RReplyForm?id=<%=dto.getId()%>&page=<%=pd.page%>">답변</a>
		</td>
	</tr>
</table>