﻿<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>메일 리스트 가입</h1>
<p>
    메일 리스트에 가입하려면,<br>
    아래 항목을 기입하고 등록 버튼을 클릭하세요.
</p>
<form action="${pageContext.request.contextPath}/add" method="post">
    성: <input type="text" name="firstName" value=""><br>
    이름: <input type="text" name="lastName" value=""><br>
    이메일: <input type="text" name="email" value=""><br>
    <input type="submit" value="등록">
</form>
<br>
<p>
    <a href="/emaillist01">리스트보기</a>
</p>
</body>
</html>