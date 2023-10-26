
<%@page import="model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
%>   
<form action="DwriteReg"  method="post" enctype="multipart/form-data">
	<table  border="">
		<tr>
		
			<td width="100px">제목</td>
			
		
		
			<td width="600px">
			<select name="dtype">
			<option valus="공포">공포</option>
			<option valus="멜로">멜로</option>
			<option valus="판타지">판타지</option>
			<option valus="드라마">드라마</option>
			<option valus="기타">기타</option>
			
			
			
			</select> 
			
			<input type="text" name="dtitle" /></td>
			
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
			<td><textarea name="dcontent" id="" cols="100" rows="40"></textarea></td>
		</tr>
		<tr>
			<td>개봉날짜</td>
			<td><input type="text" name="openDate" /></td>
		</tr>
		<tr>
			<td>마감날짜</td>
			<td><input type="text" name="closeDate" /></td>
		</tr>
			<tr>
			<td>파일</td>
			<td><input type="file" name="dfile1" /></td>
		</tr>
		<tr>
			<td>파일2</td>
			<td><input type="file" name="dfile2" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			
				<input type="submit" value="글쓰기" />
				<input type="reset" value="초기화" />
				<a href="DList?page=<%=pd.page%>">목록으로</a>
			</td>
		</tr>
	</table>
</form>