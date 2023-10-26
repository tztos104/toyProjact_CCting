<%@page import="r_model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	String id = request.getParameter("id");
%>    
<form action="RDeleteReg" method="post">
<input type="hidden" name="id" value="<%=id%>" />
<input type="hidden" name="page" value="<%=pd.page%>" />
	<table border="">
		<tr>
			<td width="200px">암호</td>
			<td width="700px"><input type="text" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			   <input type="submit" value="삭제" />
			   <a href="RDetail?id=<%=id%>&page=<%=pd.page%>">뒤로</a>
			</td>
		</tr>
	</table>
</form>