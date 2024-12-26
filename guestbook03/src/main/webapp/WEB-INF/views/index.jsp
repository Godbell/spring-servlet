<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="path" value="${pageContext.servletContext.contextPath}"/>
<jsp:useBean id="list" scope="request" type="java.util.List<guestbook.vo.GuestbookVo>"/>
<%
    pageContext.setAttribute("newLine", '\n');
%>
<c:set var="newLine" value='${pageContext.getAttribute("newLine")}'/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>방명록</title>
</head>
<body>
<form action="${path}/add" method="post">
    <table border="1">
        <tr>
            <td>이름</td>
            <td><input type="text" name="name"></td>
            <td>비밀번호</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan=4>
                <textarea name="contents" cols=60 rows=5></textarea>
            </td>
        </tr>
        <tr>
            <td colspan=4 align=right>
                <input type="submit" value="등록">
            </td>
        </tr>
    </table>
</form>

<c:forEach var="guestbook" items="${list}" varStatus="status">
    <br>
    <table width=510 border=1>
        <tr>
            <td>${guestbook.index}</td>
            <td>${guestbook.name}</td>
            <td>${guestbook.regDate}</td>
            <td><a href="${path}/delete/${guestbook.id}">삭제</a></td>
        </tr>
        <tr>
            <td colspan=4>
                    ${fn:replace(guestbook.contents, newLine, '<br>')}
            </td>
        </tr>
    </table>
</c:forEach>
</body>
</html>