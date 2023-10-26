<%@page import="r_model.RBoardDTO"%>
<%@page import="r_model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	RBoardDTO dto = (RBoardDTO)request.getAttribute("mainData");
%>  
<script>
	function fileDel() {
		
		myFrm.action="RFileDelete?page=<%=pd.page %>"
		myFrm.submit()
	}
</script>
<form name="myFrm" action="RModifyReg?page=<%=pd.page %>"  method="post" enctype="multipart/form-data">
	<table  border="">
		<tr>
			<td width="200px">id</td><td width="700px">
				<input type="text" name="id" readonly="readonly" value="<%=dto.getId() %>"/>
			</td>
		</tr>
		<tr>
			<td width="100px">분류</td>
			<td width="800px">
				<select name="rtype">
					<option>배우</option>
					<option>스태프</option>
					<option>감독</option>
				</select>
			</td>
		</tr>
		<tr>
			<td >작성자</td><td><%=dto.getNic() %></td>
		</tr>
		<tr>
			<td >작성일</td><td><%=dto.getReg_dateStr() %></td>
		</tr>
		<tr>
			<td >조회수</td><td><%=dto.getCnt() %></td>
		</tr>

		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="<%=dto.getTitle() %>"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" id="" cols="30" rows="10"><%=dto.getContent() %></textarea></td>
		</tr>
		<% if(dto.getSeq()==0) { %>
		<tr>
			<td>파일</td>
			<td>
			<% if(dto.getUpfile().equals("")) {%>
				<input type="file" name="upfile" />
			<% } else { %>
				<%=dto.getUpfile() %><input type="button" value="파일삭제" onclick="fileDel()" />
			<% } %>
			</td>
		</tr>
		<% } %>
		<tr>
			<td>암호</td>
			<td><input type="text" name="pw" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			
				<input type="submit" value="수정" />
				<input type="reset" value="초기화" />
				<a href="RDetail?id=<%=dto.getId() %>&page=<%=pd.page%>">뒤로</a>
			</td>
		</tr>
	</table>
</form>