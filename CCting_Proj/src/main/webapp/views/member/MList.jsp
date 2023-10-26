<%@page import="java.util.ArrayList"%>
<%@page import="model_p.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    int itemsPerPage = 10;
    ArrayList<MemberDTO> mainData = (ArrayList<MemberDTO>) request.getAttribute("mainData");
    int totalItems = mainData.size();
    int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

    String pageParam = request.getParameter("page");
    int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

    int startIndex = (currentPage - 1) * itemsPerPage;
    int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

    ArrayList<MemberDTO> displayedData = new ArrayList<>(mainData.subList(startIndex, endIndex));
%>

<table border="" align="center" width="99%" style="border-collapse: collapse;">
    <tr align="center">
        <td width="">번호</td>
        <td width="">아이디</td>
        <td width="">이름</td>
        <td width="">닉네임</td>
        <td width="">전화번호</td>
        <td width="">이메일</td>
        <td width="">주소</td>
        <td width="">생년월일</td>
        <td width="">회원구분</td>
    </tr>
    <% 
        int i = startIndex + 1;
        for (MemberDTO dto : displayedData) {
    %>
    <tr align="center">
        <td><%=i %></td>
        <td><%=dto.getMid() %></td>
        <td><%=dto.getMname() %></td>
        <td><%=dto.getMnic() %></td>
        <td><%=dto.getMphone() %></td>
        <td><%=dto.getMemail() %></td>
        <td><%=dto.getMaddress() %></td>
        <td><%=dto.getMbirth() %></td>
        <td><%=dto.getMtype() %></td>
    </tr>
    <% 
        i++;
        } 
    %>
    
    <tr>
        <td colspan="9" align="center">
            <%
                if (currentPage > 1) {
            %>
            <a href="?page=<%=currentPage - 1 %>">◀</a>
            <% 
                } 
                for (int p = 1; p <= totalPages; p++) { 
                    if (currentPage == p) {
            %>
            <%=p %>
            <% 
                } else { 
            %>
            <a href="?page=<%=p %>"><%=p %></a>
            <% 
                } 
                } 
                if (currentPage < totalPages) {
            %>
            <a href="?page=<%=currentPage + 1 %>">▶</a>
            <% 
                } 
            %>
        </td>
    </tr>
</table>
