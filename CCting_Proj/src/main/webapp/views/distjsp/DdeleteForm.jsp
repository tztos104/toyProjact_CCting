
<%@page import="model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	String did = request.getParameter("did");
%>    
<form action="DdeleteReg" method="post">
<input type="hidden" name="did" value="<%=did%>" />
<input type="hidden" name="page" value="<%=pd.page%>" />
	<table border="">
		<tr>
			<td width="200px">암호</td>
			<td width="700px"><input type="text" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			   <input type="submit" value="삭제" />
			   <a href="Ddetail?did=<%=did%>&page=<%=pd.page%>">뒤로</a>
			</td>
		</tr>
	</table>
</form>