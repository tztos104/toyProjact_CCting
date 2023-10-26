<%@page import="r_model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
%> 
<style>
input[type=text] {width: 800px;}
textarea {width : 800px; height: 500px;}
</style>  
<form action="RWriteReg"  method="post" enctype="multipart/form-data">
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
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" id="" cols="30" rows="10"></textarea></td>
		</tr>
		<tr>
			<td>파일</td>
			<td><input type="file" name="upfile" /></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="text" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			
				<input type="submit" value="글쓰기" />
				<input type="reset" value="초기화" />
				<a href="RList?page=<%=pd.page%>">목록으로</a>
			</td>
		</tr>
	</table>
</form>