
<%@page import="model.DistDTO"%>
<%@page import="model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	DistDTO dto = (DistDTO)request.getAttribute("mainData");
%>    
<table border="">
	<tr><td width="200px">id</td><td colspan="3" width="700px"><%=dto.getDid() %></td></tr>
	<tr><td >제목</td><td><%=dto.getDtitle() %></td><td >조회수</td><td><%=dto.getCnt() %></td></tr>
	<tr><td >작성자</td><td colspan="3" ><%=dto.getDname() %></td>	</tr>
	<tr><td >작성일</td><td colspan="3" ><%=dto.getReg_datestr() %></td></tr>
	<tr><td >타입</td><td><%=dto.getDtype() %></td></tr>
	
	<tr>
		<td >내용</td> 
		<td><%=dto.getDcontentbr() %></td>
	</tr>
	
	
	<tr>
		
	
	</tr><tr>
		<td >파일</td><td>
		<% if(dto.getIsImg()) {%>
		 	<img width="500px" src="/CCting_Proj/up/<%=dto.getDfile1() %>" alt="" /> 
		<%} else { %>
		 	<a href="/CCting_Proj/noneJsp/FileDown?fName=<%=dto.getDfile1() %>"><%=dto.getDfile1() %></a> 
		<%} %>
		</td>
	
	<% if(!dto.getDfile2().equals("")) {%>	
	</tr><tr>
		<td >파일</td><td>
		<% if(dto.getIsImg()) {%>
		 	<img width="500px" src="/CCting_Proj/up/<%=dto.getDfile2() %>" alt="" /> 
		<%} else { %>
		 	<a href="/CCting_Proj/noneJsp/FileDown?fName=<%=dto.getDfile2() %>"><%=dto.getDfile2() %></a> 
		<%} %>
		</td>
	<%} %>
	</tr>
	<tr>
		<td colspan="2"  align="right">
			<a href="DList?page=<%=pd.page%>">목록으로</a>
			<a href="DdeleteForm?did=<%=dto.getDid()%>&page=<%=pd.page%>">삭제</a>
			<a href="DmodifyForm?did=<%=dto.getDid()%>&page=<%=pd.page%>">수정</a>
			<a href="DreplyForm?did=<%=dto.getDid()%>&page=<%=pd.page%>">답변</a>
		</td>
	</tr>
</table>