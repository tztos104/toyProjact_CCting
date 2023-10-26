<%@page import="r_model.RBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	RBoardDTO dto = (RBoardDTO)request.getAttribute("mainData");
%>  

<form action="RReplyReg"  method="post">
	<input type="hidden" name="gid" value="<%=dto.getGid() %>" />
	<input type="hidden" name="seq" value="<%=dto.getSeq() %>" />
	<input type="hidden" name="lev" value="<%=dto.getLev() %>" />
	<table  border="">
		<tr>
			<td width="100px">분류</td>
			<td width="800px">
				<select name="rtype">
					<option value="배우">배우</option>
					<option value="스태프">스태프</option>
					<option value="감독">감독</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="nic" /></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="[Re]<%=dto.getTitle() %>"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" id="" cols="30" rows="10">[Re]<%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="text" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			
				<input type="submit" value="답변하기" />
				<input type="reset" value="초기화" />
				<a href="BDetail?id=<%=dto.getId() %>">뒤로</a>
			</td>
		</tr>
	</table>
</form>