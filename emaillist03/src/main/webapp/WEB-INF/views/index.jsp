<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>메일 리스트에 가입되었습니다.</h1>
<p>입력한 정보 내역입니다.</p>

<jsp:useBean id="list" scope="request" type="java.util.List<emaillist.vo.EmaillistVo>"/>
<c:forEach var="email" items="${list}">
    <table border="1" cellpadding="5" cellspacing="2">
        <tr>
            <td align=right>First name:</td>
            <td>${email.firstName}</td>
        </tr>
        <tr>
            <td align=right width="110">Last name:</td>
            <td width="110">${email.lastName}</td>
        </tr>
        <tr>
            <td align=right>Email address:</td>
            <td>${email.email}</td>
        </tr>
    </table>
</c:forEach>
<br>

<p>
    <a href="form.jsp">추가메일 등록</a>
</p>
<br>
</body>
</html>