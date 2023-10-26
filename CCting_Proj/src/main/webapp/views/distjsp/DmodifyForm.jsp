
<%@page import="model.DistDTO"%>
<%@page import="model.PageData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageData pd = (PageData)request.getAttribute("pd");
	DistDTO dto = (DistDTO)request.getAttribute("mainData");
%>  
<script>
	function fileDel() {
		
		myFrm.action="DFileDelete?page=<%=pd.page %>"
		myFrm.submit()
	}
</script>
<form name="myFrm" action="DModifyReg?page=<%=pd.page %>"  method="post" enctype="multipart/form-data">
	<table  border="">
		<tr>
			<td width="200px">id</td><td width="700px">
				<input type="text" name="did" readonly="readonly" value="<%=dto.getDid() %>"/>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="dtitle" value="<%=dto.getDtitle() %>"/></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="dname" value="<%=dto.getDname() %>" /></td>
		</tr>
		<tr>
			<td >작성일</td><td><%=dto.getReg_datestr() %></td>
		</tr>
		<tr>
			<td >조회수</td><td><%=dto.getCnt() %></td>
		<tr>
			<td>암호</td>
			<td><input type="text" name="dpw" /></td>
		</tr>
		
		<% if(dto.getSeq()==0) { %>
		<tr>
			<td>파일</td>
			<td>
			<% if(dto.getDfile1().equals("")) {%>
				<input type="file" name="dfile1" />
			<% } else { %>
				<%=dto.getDfile1() %><input type="button" value="파일삭제" onclick="fileDel()" />
			<% } %>
				<% if(dto.getDfile2().equals("")) {%>
				<input type="file" name="dfile2" />
			<% } else { %>
				<%=dto.getDfile2() %><input type="button" value="파일삭제" onclick="fileDel()" />
			<% } %>
			</td>
		</tr>
		<% } %>
		<tr>
			<td>내용</td>
			<td><textarea name="dcontent" id="" cols="30" rows="10"><%=dto.getDcontent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			
				<input type="submit" value="수정" />
				<input type="reset" value="초기화" />
				<a href="Ddetail?did=<%=dto.getDid()%>&page=<%=pd.page%>">뒤로</a>
			</td>
		</tr>
	</table>
</form>