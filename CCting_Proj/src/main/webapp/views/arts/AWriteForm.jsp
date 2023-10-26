<%@page import="model_p.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
%>
<form action="AWriteReg" method="post" enctype="multipart/form-data">
	<table border="">
		<tr>
			<td width="100px">작성자</td>
			<td width="800px"><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td>나이</td>
			<td>만 <input type="text" name="age">세</td>
		</tr>
		<tr>
			<td>키</td>
			<td><input type="text" name="height">cm</td>
		</tr>
		<tr>
			<td>체중</td>
			<td><input type="text" name="weight">kg</td>
		</tr>
		<tr>
			<td>소속사</td>
			<td><input type="text" name="agency"></td>
		</tr>
		<tr>
			<td>출연작품</td>
			<td><textarea name="arts" cols="30" rows="10"></textarea></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" cols="30" rows="10"></textarea></td>
		</tr>
		<tr>
			<td>수상내역</td>
			<td><textarea name="awards" cols="30" rows="10"></textarea></td>
		</tr>
		<tr>
			<td>사진1-프로필</td>
			<td><input type="file" name="photo1"></td>
		</tr>
		<tr>
			<td>사진2-전신사진</td>
			<td><input type="file" name="photo2"></td>
		</tr>
		<tr>
			<td>사진3-작품사진</td>
			<td><input type="file" name="bfile2"></td>
		</tr>
		<tr>
			<td>파일1-목소리</td>
			<td><input type="file" name="bfile1"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글쓰기">
				<input type="reset" value="초기화">
				<a href="AList?page=<%=pd.page %>">목록으로</a>
			</td>
		</tr>
	</table>
</form>
