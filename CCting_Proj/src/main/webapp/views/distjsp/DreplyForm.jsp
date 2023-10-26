
<%@page import="model.DistDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	DistDTO dto = (DistDTO)request.getAttribute("mainData");
%>  

<form action="DreplyReg"  method="post">
	<input type="hidden" name="gid" value="<%=dto.getGid() %>" />
	<input type="hidden" name="seq" value="<%=dto.getSeq() %>" />
	<input type="hidden" name="lev" value="<%=dto.getLev() %>" />
	<table  border="">
		<tr>
			<td>제목</td>
			<td><input type="text" name="dtitle" value="[Re]<%=dto.getDtitle() %>"/></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="dname" /></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="text" name="dpw" /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="dcontent" id="" cols="30" rows="10">[Re]<%=dto.getDcontent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			
				<input type="submit" value="답변하기" />
				<input type="reset" value="초기화" />
				<a href="Ddetail?did=<%=dto.getDid() %>">뒤로</a>
			</td>
		</tr>
	</table>
</form>